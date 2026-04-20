package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import org.springframework.stereotype.Repository;

/**
 * Implementación del repositorio de categorías utilizando almacenamiento en memoria (Map)[cite: 177, 180].
 * Gestionada como bean de Spring mediante la anotación {@link Repository}[cite: 182].
 * * @see RepositorioMapAbstracto
 * @see CategoriaRepository
 */
@Repository
public class CategoriaRepositoryImpl
        extends MapCategoriaRepository<Categoria, Long>
        implements CategoriaRepository {

    /**
     * Obtiene el identificador único (Long) de la categoría.
     * @param entity Instancia de la categoría.
     * @return Identificador de la entidad.
     */
    @Override
    protected Long getId(Categoria entity) {
        return entity.getId();
    }
}