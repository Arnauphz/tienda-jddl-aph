package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    private MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public String mostrarMarcas(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        return "marcas/marcas";
    }

    @GetMapping("/{id}")
    public String detalleMarca(@PathVariable Long id, Model model) {
        Marca marca = marcaService.findById(id); // lanza excepción si no existe

        List<Producto> productosOrdenados = marca.getProductos()
                .stream()
                .sorted(Comparator.comparing(
                        Producto::getNombre,
                        String.CASE_INSENSITIVE_ORDER
                ))
                .toList();

        model.addAttribute("marca", marca);
        model.addAttribute("productos", productosOrdenados);

        return "marcas/detalle-marca";
    }
}