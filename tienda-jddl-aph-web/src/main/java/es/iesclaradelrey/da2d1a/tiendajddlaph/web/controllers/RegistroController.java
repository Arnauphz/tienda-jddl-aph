package es.iesclaradelrey.da2d1a.tiendajddlaph.web.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.EmailYaRegistradoException;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.UsuarioService;
import es.iesclaradelrey.da2d1a.tiendajddlaph.web.models.RegistroUsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador del registro de nuevos usuarios.
 *
 * GET  /register  → muestra el formulario de registro.
 * POST /register  → procesa el formulario:
 *                   - Si las condiciones no están aceptadas o el email ya
 *                     está registrado, vuelve a la plantilla con un mensaje.
 *                   - Si todo es correcto, crea el usuario y redirige al login.
 *
 * El acceso a /register sólo debe permitirse a usuarios anónimos. Eso se
 * configura en ConfiguracionSeguridad (paso 5), no aquí.
 */
@Controller
@RequestMapping("/register")
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Muestra el formulario de registro vacío.
     */
    @GetMapping
    public String mostrarFormulario(Model model) {
        // Pasamos un DTO vacío al modelo para que Thymeleaf lo use con th:object.
        // Si no estuviera presente, th:field daría error al renderizar la vista.
        if (!model.containsAttribute("registro")) {
            model.addAttribute("registro", new RegistroUsuarioDTO());
        }
        return "register";
    }

    /**
     * Procesa el envío del formulario de registro.
     *
     * @ModelAttribute("registro") indica a Spring MVC que tome los campos
     * del POST y los enlace al DTO con el mismo nombre que aparece en
     * th:object="${registro}" en la plantilla.
     */
    @PostMapping
    public String procesarFormulario(@ModelAttribute("registro") RegistroUsuarioDTO registro,
                                     Model model) {

        // 1. Validación de UI: las condiciones deben estar aceptadas.
        //    Esta validación es de UX/legal, no de negocio, por eso vive en
        //    el controlador y no en el servicio.
        if (!registro.isAceptaCondiciones()) {
            model.addAttribute("error",
                    "Debes aceptar las condiciones para registrarte.");
            // Devolvemos la misma vista; @ModelAttribute deja "registro" en el
            // modelo automáticamente, así que los campos rellenados se mantienen.
            return "register";
        }

        // 2. Llamada al servicio: si el email ya existe, lanza excepción.
        try {
            usuarioService.registrar(
                    registro.getEmail(),
                    registro.getNombre(),
                    registro.getApellidos(),
                    registro.getTelefono(),
                    registro.getFechaNacimiento(),
                    registro.getPassword()
            );
        } catch (EmailYaRegistradoException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        } catch (RuntimeException e) {
            // Cualquier otro fallo inesperado (BD caída, restricción rara, etc.)
            // se muestra al usuario sin reventar la página.
            model.addAttribute("error",
                    "Ha ocurrido un error al registrar el usuario. Inténtalo de nuevo.");
            return "register";
        }

        // 3. Éxito: redirigimos al login. Spring Security mostrará el formulario
        //    de login estándar; el usuario podrá entrar con sus credenciales recién
        //    creadas. El parámetro ?registered podría usarse para mostrar un
        //    mensaje de bienvenida en el login (lo dejamos como mejora futura).
        return "redirect:/login";
    }
}