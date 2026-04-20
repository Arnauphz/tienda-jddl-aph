package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador encargado de gestionar el acceso a la página principal del sitio.
 */
@Controller
public class ControladorInicio {

    private final CategoriaService categoriaService;

    /**
     * Constructor con inyección del servicio necesario para mostrar datos en la Home.
     */
    public ControladorInicio(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Atiende la petición raíz y carga las categorías para la vista de inicio.
     * @return Nombre de la plantilla index.
     */
    @GetMapping("/")
    public String mostrarVista(Model model){
        model.addAttribute("categorias", categoriaService.findAll());
        return "index";
    }

}