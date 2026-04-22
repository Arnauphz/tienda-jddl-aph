package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String mostrarDetalleCategoria(@PathVariable Long id, Model model) {
        Optional<Categoria> optional =  categoriaService.findById(id);
        Categoria categoria = optional.orElse(null);
        model.addAttribute("categoria", categoria);
        return "categorias/detalles-categoria";
    }
}