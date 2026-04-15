package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import java.util.*;

abstract class RepositorioMapAbstracto <T, ID> implements Repositorio<T, ID>{
    private final Map <ID, T> map = new HashMap<>();

    protected abstract ID getId(T entity);

    public void guardar (T entity){
        ID id = getId(entity);
        map.put(id, entity);
    }

    public Iterable<T> findAll(){
        return map.values();
    }

    public Optional<T> findById(ID id){
        return Optional.ofNullable(map.get(id));
    }
}
