package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String formularioNuevo(Model model) {
        model.addAttribute("marca", new Marca());
        return "admin/marcas/formulario";
    }

    @GetMapping("/{id}/edit")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("marca", marcaService.findById(id));
        return "admin/marcas/formulario";
    }

    @PostMapping("/new")
    public String guardar(@ModelAttribute Marca marca, Model model) {
        try {
            marcaService.guardar(marca);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("marca", marca);
            model.addAttribute("error", e.getMessage());
            return "admin/marcas/formulario";
        }
    }

    @GetMapping("/{id}/delete")
    public String confirmarBorrado(@PathVariable Long id, Model model) {
        model.addAttribute("marca", marcaService.findById(id));
        return "admin/marcas/confirmar-borrado";
    }

    @PostMapping("/{id}/delete")
    public String borrar(@PathVariable Long id, Model model) {
        try {
            marcaService.deleteById(id);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("marca", marcaService.findById(id));
            model.addAttribute("error", e.getMessage());
            return "admin/marcas/confirmar-borrado";
        }
    }
}