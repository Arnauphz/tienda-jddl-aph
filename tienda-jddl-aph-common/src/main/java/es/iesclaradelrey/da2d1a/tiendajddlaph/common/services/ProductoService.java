package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;

import java.util.Optional;

public interface ProductoService {
    void guardar(Producto entity);
    Optional<Producto> findById(Long id);
    Iterable<Producto> findAll();
}
