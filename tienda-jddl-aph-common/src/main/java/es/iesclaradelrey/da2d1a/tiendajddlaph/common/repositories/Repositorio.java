package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import java.util.Optional;

interface Repositorio <T, ID> {
    void guardar (T entity);
    public  Iterable<T> findAll();
    public Optional<T> findById(ID id);
}