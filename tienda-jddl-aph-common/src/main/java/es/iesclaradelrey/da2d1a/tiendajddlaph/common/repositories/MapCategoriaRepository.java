package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import java.util.*;

/**
 * Implementación base genérica para repositorios que utilizan un {@link Map} como almacenamiento en memoria.
 * @param <T> Tipo de la entidad gestionada.
 * @param <ID> Tipo del identificador de la entidad.
 */
abstract class MapCategoriaRepository<T, ID> implements Repository<T, ID> {

    /** Almacenamiento interno de las entidades indexadas por su ID. */
    private final Map <ID, T> map = new HashMap<>();

    /**
     * Método abstracto para obtener el identificador de una entidad.
     * Debe implementarse en las subclases según el tipo de entidad.
     */
    protected abstract ID getId(T entity);

    /**
     * Guarda o actualiza una entidad en el mapa.
     */
    public void guardar (T entity){
        ID id = getId(entity);
        map.put(id, entity);
    }

    /**
     * Devuelve todas las entidades almacenadas en el mapa.
     */
    public Iterable<T> findAll(){
        return map.values();
    }

    /**
     * Busca una entidad por su identificador.
     * @return Un {@link Optional} que contiene la entidad si se encuentra.
     */
    public Optional<T> findById(ID id){
        return Optional.ofNullable(map.get(id));
    }
}