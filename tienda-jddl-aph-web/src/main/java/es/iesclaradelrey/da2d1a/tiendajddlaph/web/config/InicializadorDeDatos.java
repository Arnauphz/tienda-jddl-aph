package es.iesclaradelrey.da2d1a.tiendajddlaph.web.config;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.CategoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializadorDeDatos implements CommandLineRunner {

    private final CategoriaService categoriaService;

    public InicializadorDeDatos(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria();
        cat1.setId(1L);
        cat1.setNombre("Camisetas");
        cat1.setDescripcion("Equipaciones de fútbol, camisetas y ropa deportiva");
        cat1.setImagen("imagenCat.jpg");
        categoriaService.guardar(cat1);

        Categoria cat2 = new Categoria();
        cat2.setId(2L);
        cat2.setNombre("Pantalones");
        cat2.setDescripcion("Pantalones y ropa deportiva para entrenamiento y partido");
        cat2.setImagen("imagenCat.jpg");
        categoriaService.guardar(cat2);

        Categoria cat3 = new Categoria();
        cat3.setId(3L);
        cat3.setNombre("Balones");
        cat3.setDescripcion("Balones y material deportivo para fútbol");
        cat3.setImagen("imagenCat.jpg");
        categoriaService.guardar(cat3);

        Categoria cat4 = new Categoria();
        cat4.setId(4L);
        cat4.setNombre("Accesorios");
        cat4.setDescripcion("Medias, guantes, mochilas y otros complementos");
        cat4.setImagen(null);
        categoriaService.guardar(cat4);
    }
}