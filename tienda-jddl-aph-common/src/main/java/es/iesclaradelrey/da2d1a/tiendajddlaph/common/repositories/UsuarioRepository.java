package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su email. Se utilizará en el UserDetailsService
     * para localizar al usuario que intenta autenticarse.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Indica si ya existe un usuario con el email dado.
     * Se utilizará en el formulario de registro para validar duplicados.
     */
    boolean existsByEmail(String email);
}
