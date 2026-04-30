package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.CategoriaService;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.MarcaService;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

    private final ProductoService productoService;
    private final MarcaService marcaService;
    private final CategoriaService categoriaService;

    public AdminProductoController(ProductoService productoService, MarcaService marcaService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.marcaService = marcaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listado(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "admin/productos/listado.html";
    }

    @GetMapping("/")
    public String listado2() {
        return "redirect:/admin/productos";
    }

    @GetMapping("/new")
    public String formularioProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/productos/formulario";
    }

    @PostMapping("/new")
    public String guardarProducto(
            @ModelAttribute Producto producto,
            @RequestParam(value = "marcaId", required = false) Long marcaId,
            @RequestParam(value = "categoriasIds", required = false) List<Long> categoriasIds,
            Model model) {
        try {
            // Vincular la marca manualmente desde su ID
            if (marcaId != null) {
                producto.setMarca(marcaService.findById(marcaId));
            }

            // Vincular las categorías manualmente desde sus IDs
            if (categoriasIds != null) {
                List<Categoria> cats = categoriasIds.stream()
                        .map(id -> categoriaService.findById(id))
                        .toList();
                producto.setCategorias(cats);
            } else {
                producto.setCategorias(new ArrayList<>());
            }

            productoService.guardar(producto);
            return "redirect:/admin/productos";

        } catch (Exception e) {
            model.addAttribute("producto", producto);
            model.addAttribute("marcas", marcaService.findAll());
            model.addAttribute("categorias", categoriaService.findAll());
            model.addAttribute("error", e.getMessage());
            return "admin/productos/formulario";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoService.findById(id));
        // Si el form necesita listas auxiliares (marcas, categorías):
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/productos/formulario";
    }
}
