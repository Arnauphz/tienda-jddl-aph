package es.iesclaradelrey.da2d1a.tiendajddlaph.security;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementación propia de UserDetailsService.
 *
 * Conecta el flujo de autenticación de Spring Security con la entidad Usuario
 * de la aplicación: dado un email, devuelve el UserDetails correspondiente
 * o lanza UsernameNotFoundException si no existe.
 *
 * Está en el módulo "security" porque depende de Spring Security
 * (UserDetailsService, UsernameNotFoundException). Para acceder a los datos
 * delega en UsuarioService, que vive en "common" y no sabe nada de seguridad.
 *
 * Nota sobre el nombre del método loadUserByUsername:
 * Lo impone la interfaz UserDetailsService de Spring Security; no se puede
 * traducir. Igual ocurre con la excepción UsernameNotFoundException.
 */
@Service
public class MiUsuarioDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public MiUsuarioDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Spring Security llama a este método con el "username" que el usuario
     * tecleó en el formulario de login. En nuestra aplicación, ese valor
     * es el email (ver MiUsuarioDetails#getUsername).
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService.findByEmail(username)
                .map(MiUsuarioDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No existe ningún usuario con email: " + username));
    }
}
