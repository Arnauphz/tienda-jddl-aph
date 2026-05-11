package es.iesclaradelrey.da2d1a.tiendajddlaph.api.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.mappers.CategoriaMapper;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.mappers.ProductoMapper;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.CategoriaRepository;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.ProductoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriaApiController {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final CategoriaMapper categoriaMapper;
    private final ProductoMapper productoMapper;

    public CategoriaApiController(CategoriaRepository categoriaRepository,
                                  ProductoRepository productoRepository,
                                  CategoriaMapper categoriaMapper,
                                  ProductoMapper productoMapper) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.categoriaMapper = categoriaMapper;
        this.productoMapper = productoMapper;
    }

    // 3.5 - Listado de categorías en orden alfabético
    @GetMapping
    public List<CategoriaDto> listarCategorias() {
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        return categoriaMapper.toDtoList(categoriaRepository.findAll(sort));
    }

    // 3.7 - Listado de productos por categoría en orden alfabético
    @GetMapping("/{categoryId}/products")
    public List<ProductoDto> listarProductosPorCategoria(@PathVariable Long categoryId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        return productoMapper.toDtoList(
                productoRepository.findByCategorias_Id(categoryId, sort)
        );
    }
}