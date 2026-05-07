package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Usuario;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Servicio de aplicación para la gestión de usuarios.
 */
public interface UsuarioService {

    /**
     * Devuelve el usuario con el email indicado, si existe.
     * Lo usará el UserDetailsService para localizar al usuario que intenta
     * autenticarse.
     */
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long id);

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * Codifica la contraseña con BCrypt, asigna la fecha de registro al
     * momento actual y persiste el usuario en BD.
     *
     * @param email           email único; identificador de login
     * @param nombre          nombre del usuario
     * @param apellidos       apellidos del usuario
     * @param telefono        teléfono (puede ser null)
     * @param fechaNacimiento fecha de nacimiento (puede ser null)
     * @param passwordPlano   contraseña en texto plano; se codifica internamente
     * @return el usuario recién creado, con id y fechaRegistro asignados
     * @throws EmailYaRegistradoException si ya existe un usuario con ese email
     */
    Usuario registrar(String email,
                      String nombre,
                      String apellidos,
                      String telefono,
                      LocalDate fechaNacimiento,
                      String passwordPlano);
}
