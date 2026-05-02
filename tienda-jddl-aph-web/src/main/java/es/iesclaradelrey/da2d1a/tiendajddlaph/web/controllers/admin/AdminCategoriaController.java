package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categorias")
public class AdminCategoriaController {

    private final CategoriaService categoriaService;

    public AdminCategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listado(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/categorias/listado";
    }

    @GetMapping("/new")
    public String formularioNuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categorias/formulario";
    }

    @GetMapping("/{id}/edit")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.findById(id));
        return "admin/categorias/formulario";
    }

    @PostMapping("/new")
    public String guardar(@ModelAttribute Categoria categoria, Model model) {
        try {
            categoriaService.guardar(categoria);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("error", e.getMessage());
            return "admin/categorias/formulario";
        }
    }

    @GetMapping("/{id}/delete")
    public String confirmarBorrado(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.findById(id));
        return "admin/categorias/confirmar-borrado";
    }

    @PostMapping("/{id}/delete")
    public String borrar(@PathVariable Long id, Model model) {
        try {
            categoriaService.deleteById(id);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("categoria", categoriaService.findById(id));
            model.addAttribute("error", e.getMessage());
            return "admin/categorias/confirmar-borrado";
        }
    }
}