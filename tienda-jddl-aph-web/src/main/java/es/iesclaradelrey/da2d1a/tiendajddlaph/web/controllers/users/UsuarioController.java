package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers.users;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.UsuarioService;
import es.iesclaradelrey.da2d1a.tiendajddlaph.security.MiUsuarioDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controlador del área "users".
 *
 * Las URLs:
 *  - /users/profile          → perfil del usuario autenticado
 *  - /users/profile/{userId} → perfil del usuario con id userId
 *
 * Las reglas de autorización están delegadas en Spring Security mediante
 * @PreAuthorize: el método ni siquiera se ejecuta si el usuario no cumple
 * la regla. Esto separa "qué puede ver cada uno" (responsabilidad de
 * seguridad) de "cómo se prepara la vista" (responsabilidad del controlador).
 */
@Controller
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Muestra el perfil del usuario autenticado.
     * URL: GET /users/profile
     *
     * No lleva @PreAuthorize porque el matcher en ConfiguracionSeguridad
     * ya restringe /users/profile/** a usuarios autenticados; aquí
     * basta con que el usuarioDetails inyectado nunca sea null.
     */
    @GetMapping("/profile")
    public String verPerfil(@AuthenticationPrincipal MiUsuarioDetails usuarioDetails,
                            Model model) {
        model.addAttribute("usuario", usuarioDetails.getUsuario());
        return "users/profile";
    }

    /**
     * Muestra el perfil del usuario con el id indicado.
     * URL: GET /users/profile/{userId}
     *
     * Regla de autorización (@PreAuthorize con SpEL):
     *   - hasRole('ADMIN'): los administradores pueden ver cualquier perfil.
     *   - #userId == authentication.principal.usuario.id:
     *       el usuario autenticado puede ver su PROPIO perfil.
     *
     * Si la regla no se cumple, Spring Security lanza una excepción y
     * devuelve HTTP 403 antes de entrar siquiera al método. Esto es lo
     * que se llama "fail closed": por defecto se deniega el acceso.
     *
     * Nota: la expresión #userId hace referencia al parámetro del método
     * con ese mismo nombre, gracias a la inferencia de nombres de Spring.
     */
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.usuario.id")
    @GetMapping("/profile/{userId}")
    public String verPerfilPorId(@PathVariable Long userId, Model model) {

        Usuario usuario = usuarioService.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("usuario", usuario);
        return "users/profile";
    }
}