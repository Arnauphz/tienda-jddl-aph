package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio CRUD para la entidad Rol.
 *
 * El segundo parámetro es String porque la PK de Rol es su id de tipo
 * String (p. ej. "ADMIN"), no Long.
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, String> {
}
