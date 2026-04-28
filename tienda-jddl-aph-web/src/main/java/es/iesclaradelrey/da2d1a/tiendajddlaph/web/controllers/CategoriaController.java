package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Controlador para la gestión de las vistas relacionadas con las categorías.
 */
@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    /**
     * Constructor con inyección del servicio de categorías.
     */
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Muestra el listado completo de categorías.
     * @return Nombre de la plantilla de la lista de categorías.
     */
    @GetMapping
    public String mostrarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categorias/categorias";
    }

    /**
     * Muestra el detalle de una categoría específica.
     * @param id Identificador de la categoría.
     * @return Nombre de la plantilla de detalles.
     */
    @GetMapping("/{id}")
    public String detalleCategoria(@PathVariable Long id, Model model) {
        Optional<Categoria> optionalCategoria = categoriaService.findById(id);

        if (optionalCategoria.isEmpty()) {
            return "redirect:/categorias";
        }

        Categoria categoria = optionalCategoria.get();

        List<Producto> productosOrdenados = categoria.getProductos()
                .stream()
                .sorted(Comparator.comparing(
                        Producto::getNombre,
                        String.CASE_INSENSITIVE_ORDER
                ))
                .toList();

        model.addAttribute("categoria", categoria);
        model.addAttribute("productos", productosOrdenados);

        return "categorias/detalles-categoria";
    }


}