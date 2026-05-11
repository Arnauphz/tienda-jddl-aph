package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de lógica de negocio para las categorías.
 */
public interface CategoriaService {

    /**
     * Guarda o actualiza una categoría.
     */
    void guardar(Categoria entity);

    /**
     * Recupera todas las categorías disponibles.
     */
    Iterable<Categoria> findAll();

    /**
     * Busca una categoría por su ID.
     * @param id Identificador de la categoría.
     * @return {@link Optional} con el resultado.
     */
    Categoria findById(Long id);
    void deleteById(Long id);
}