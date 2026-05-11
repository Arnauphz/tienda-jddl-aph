package es.iesclaradelrey.da2d1a.tiendajddlaph.api.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.AddToCartRequestDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.CarritoDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.LineaCarritoDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions.ProductoNoEnCarritoException;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions.ProductoNotFoundException;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions.StockInsuficienteException;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.LineaCarrito;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.LineaCarritoRepository;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.ProductoRepository;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CarritoController {

    private final LineaCarritoRepository lineaCarritoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarritoController(LineaCarritoRepository lineaCarritoRepository,
                             ProductoRepository productoRepository,
                             UsuarioRepository usuarioRepository) {
        this.lineaCarritoRepository = lineaCarritoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // 3.2.1 — POST /api/v1/cart — Añadir producto al carrito
    @PostMapping
    public ResponseEntity<CarritoDto> addToCart(
            @RequestBody AddToCartRequestDto request,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Verificar unidades > 0
        if (request.getUnidades() == null || request.getUnidades() <= 0) {
            throw new IllegalArgumentException(
                    "Las unidades deben ser mayor que cero. Recibido: " + request.getUnidades());
        }

        // Verificar que existe el producto
        Producto producto = productoRepository.findById(request.getProductoId())
                .orElseThrow(() -> new ProductoNotFoundException(request.getProductoId()));

        // Verificar stock suficiente
        if (producto.getStock() < request.getUnidades()) {
            throw new StockInsuficienteException(
                    producto.getId(), producto.getStock(), request.getUnidades());
        }

        String email = userDetails.getUsername();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Si ya existe la línea, incrementar unidades; si no, crearla
        Optional<LineaCarrito> lineaExistente =
                lineaCarritoRepository.findByUsuarioEmailAndProductoId(email, producto.getId());

        if (lineaExistente.isPresent()) {
            LineaCarrito linea = lineaExistente.get();
            linea.setUnidades(linea.getUnidades() + request.getUnidades());
            linea.setFechaActualizacion(LocalDateTime.now());
            lineaCarritoRepository.save(linea);
        } else {
            LineaCarrito nuevaLinea = LineaCarrito.builder()
                    .usuario(usuario)
                    .producto(producto)
                    .unidades(request.getUnidades())
                    .fechaActualizacion(LocalDateTime.now())
                    .build();
            lineaCarritoRepository.save(nuevaLinea);
        }

        return ResponseEntity.ok(buildCarritoDto(email));
    }

    // 3.2.2 — GET /api/v1/cart — Listado del carrito
    @GetMapping
    public ResponseEntity<CarritoDto> getCarrito(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(buildCarritoDto(userDetails.getUsername()));
    }

    // 3.2.3 — DELETE /api/v1/cart/{productId} — Eliminar un producto del carrito
    @DeleteMapping("/{productId}")
    public ResponseEntity<CarritoDto> removeFromCart(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();

        // Verificar que existe el producto
        if (!productoRepository.existsById(productId)) {
            throw new ProductoNotFoundException(productId);
        }

        // Verificar que el producto está en el carrito
        LineaCarrito linea = lineaCarritoRepository
                .findByUsuarioEmailAndProductoId(email, productId)
                .orElseThrow(() -> new ProductoNoEnCarritoException(productId));

        lineaCarritoRepository.delete(linea);

        return ResponseEntity.ok(buildCarritoDto(email));
    }

    // 3.2.4 — DELETE /api/v1/cart — Vaciar el carrito
    @DeleteMapping
    public ResponseEntity<CarritoDto> clearCarrito(
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        lineaCarritoRepository.deleteByUsuarioEmail(email);

        return ResponseEntity.ok(buildCarritoDto(email));
    }

    // --- Método auxiliar para construir el CarritoDto ---
    private CarritoDto buildCarritoDto(String email) {
        List<LineaCarrito> lineas = lineaCarritoRepository.findByUsuarioEmail(email);

        List<LineaCarritoDto> productosDto = lineas.stream().map(linea -> {
            Producto p = linea.getProducto();
            double precioConDescuento = p.getPrecio() * (1 - p.getDescuento() / 100.0);
            double precioTotal = precioConDescuento * linea.getUnidades();

            LineaCarritoDto dto = new LineaCarritoDto();
            dto.setNombre(p.getNombre());
            dto.setPrecioUnitario(p.getPrecio());
            dto.setDescuento(p.getDescuento());
            dto.setPrecioConDescuento(Math.round(precioConDescuento * 100.0) / 100.0);
            dto.setUnidades(linea.getUnidades());
            dto.setPrecioTotal(Math.round(precioTotal * 100.0) / 100.0);
            return dto;
        }).toList();

        CarritoDto carritoDto = new CarritoDto();
        carritoDto.setProductos(productosDto);
        carritoDto.setNumProductosDistintos(
                lineaCarritoRepository.countProductosByUsuarioEmail(email));
        carritoDto.setNumUnidadesTotales(
                lineaCarritoRepository.sumUnidadesByUsuarioEmail(email));
        carritoDto.setImporteTotal(
                lineaCarritoRepository.sumImporteTotalByUsuarioEmail(email));

        return carritoDto;
    }
}