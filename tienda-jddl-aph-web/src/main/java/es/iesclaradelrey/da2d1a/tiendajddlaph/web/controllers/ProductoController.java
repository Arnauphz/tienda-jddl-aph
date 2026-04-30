package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/productos";
    }

    @GetMapping("/{id}/{slug}")
    public String detalleProducto(@PathVariable Long id,
                                  @PathVariable String slug,
                                  Model model) {
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        return "productos/detalle";
    }
}