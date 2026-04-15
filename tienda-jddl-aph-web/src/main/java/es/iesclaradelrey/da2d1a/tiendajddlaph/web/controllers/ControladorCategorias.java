package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.CategoriaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class ControladorCategorias {
    private CategoriaServicio categoriaServicio; // 1. El atributo

    public ControladorCategorias(CategoriaServicio categoriaServicio) { // 2. El constructor
        this.categoriaServicio = categoriaServicio;
    }
    @GetMapping
    public String mostrarCategorias(Model model) {
        model.addAttribute("categorias", categoriaServicio.findAll());
        return "categorias/listado";
    }
}
