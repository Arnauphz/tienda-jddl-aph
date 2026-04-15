package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;

import java.util.Optional;

public interface CategoriaServicio {
    static void guardar(Categoria entity) {

    }

    public  Iterable<Categoria> findAll();
    public Optional<Categoria> findById(Long id);
}
