package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import java.util.Optional;

/**
 * Interfaz genérica que define las operaciones básicas de persistencia.
 * @param <T> Tipo de la entidad.
 * @param <ID> Tipo del identificador.
 */
interface Repository<T, ID> {

    /**
     * Guarda o actualiza la entidad proporcionada.
     */
    void guardar (T entity);

    /**
     * Devuelve una colección con todas las entidades existentes.
     */
    public Iterable<T> findAll();

    /**
     * Busca una entidad por su identificador.
     * @return {@link Optional} con la entidad encontrada o vacío.
     */
    public Optional<T> findById(ID id);
}