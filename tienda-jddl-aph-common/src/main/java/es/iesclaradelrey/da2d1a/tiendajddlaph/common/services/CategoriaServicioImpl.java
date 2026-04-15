package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.RepositorioCategoria;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private final RepositorioCategoria repositorioCategoria;

    public CategoriaServicioImpl(RepositorioCategoria repositorioCategoria) {
        this.repositorioCategoria = repositorioCategoria;
    }

    public void guardar(Categoria entity) {
        repositorioCategoria.guardar(entity);
    }

    @Override
    public Iterable<Categoria> findAll() {
        return repositorioCategoria.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return repositorioCategoria.findById(id);
    }
}
