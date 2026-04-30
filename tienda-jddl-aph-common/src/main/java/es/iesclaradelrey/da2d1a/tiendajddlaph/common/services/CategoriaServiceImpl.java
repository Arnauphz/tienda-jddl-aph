package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación de los servicios de lógica de negocio para {@link Categoria}.
 * Anotada con {@link Service} para su gestión por el contenedor de Spring.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    /**
     * Constructor para inyección de dependencias del repositorio.
     */
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Guarda la categoría a través del repositorio.
     */
    public void guardar(Categoria entity) {
        categoriaRepository.save(entity);
    }

    /**
     * Obtiene el listado completo de categorías.
     */
    @Override
    public Iterable<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    /**
     * Busca una categoría por su identificador.
     */
    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
}