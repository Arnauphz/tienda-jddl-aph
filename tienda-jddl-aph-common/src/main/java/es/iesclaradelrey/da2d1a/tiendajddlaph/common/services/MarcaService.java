package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Marca;

import java.util.Optional;

public interface MarcaService {
    void guardar(Marca entity);
    Optional<Marca> findById(Long id);
    Iterable<Marca> findAll();
}
