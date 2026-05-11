package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

// JpaRepository ya extiende PagingAndSortingRepository, no hace falta cambiarlo.
// Solo se necesita añadir la firma que acepta Sort, que ya está en PagingAndSortingRepository.
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // findAll(Sort sort) ya está disponible por herencia de JpaRepository -> PagingAndSortingRepository
}