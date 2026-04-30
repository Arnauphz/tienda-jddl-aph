package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/marcas")
public class AdminMarcaController {

    private final MarcaService marcaService;

    public AdminMarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public String listado(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        return "admin/marcas/listado";
    }

    @GetMapping("/")
    public String listado2() {
        return "redirect:/admin/marcas";
    }

    @GetMapping("/new")
    public String formularioMarca(Model model) {
        model.addAttribute("marca", new Marca());
        return "admin/marcas/formulario";
    }

    @PostMapping("/new")
    public String guardarMarca(@ModelAttribute Marca marca, Model model) {
        try {
            marcaService.guardar(marca);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("marca", marca);
            model.addAttribute("error", e.getMessage());
            return "admin/marcas/formulario";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Marca marca = marcaService.findById(id);
        model.addAttribute("marca", marca);
        return "admin/marcas/formulario";
    }
}