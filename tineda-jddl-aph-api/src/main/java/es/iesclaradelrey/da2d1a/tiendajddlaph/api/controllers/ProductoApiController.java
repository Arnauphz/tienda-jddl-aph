package es.iesclaradelrey.da2d1a.tiendajddlaph.api.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.mappers.ProductoMapper;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.ProductoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductoApiController {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoApiController(ProductoRepository productoRepository,
                                 ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    // 3.6 - Listado de todos los productos en orden alfabético (con marca y categorías)
    @GetMapping
    public List<ProductoDto> listarProductos() {
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        return productoMapper.toDtoList(productoRepository.findAll(sort));
    }
}