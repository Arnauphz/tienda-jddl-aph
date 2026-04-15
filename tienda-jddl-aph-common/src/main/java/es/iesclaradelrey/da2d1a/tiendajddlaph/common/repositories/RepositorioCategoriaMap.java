package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioCategoriaMap
        extends RepositorioMapAbstracto<Categoria, Long>
        implements RepositorioCategoria {

    @Override
    protected Long getId(Categoria entity) {
        return entity.getId();
    }
}
