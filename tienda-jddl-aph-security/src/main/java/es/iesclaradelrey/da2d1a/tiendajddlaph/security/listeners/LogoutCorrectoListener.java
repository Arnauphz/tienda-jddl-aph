package es.iesclaradelrey.da2d1a.tiendajddlaph.security.listeners;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.EventoSeguridadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Se ejecuta cuando el usuario completa el logout con éxito.
 *
 * Tiene dos responsabilidades:
 *  1. Registrar un evento LOGOUT en la tabla de auditoría.
 *  2. Redirigir al usuario a la página inicial de la tienda
 *     (PDF 11, apartado 3.4) en lugar del /login?logout por defecto.
 *
 * Extiende SimpleUrlLogoutSuccessHandler para reaprovechar su lógica
 * de redirección (configurada vía setDefaultTargetUrl) y solo añade
 * encima el registro del evento.
 */
@Component
public class LogoutCorrectoListener extends SimpleUrlLogoutSuccessHandler {

    private final EventoSeguridadService eventoSeguridadService;

    public LogoutCorrectoListener(EventoSeguridadService eventoSeguridadService) {
        this.eventoSeguridadService = eventoSeguridadService;

        // URL a la que redirigir tras un logout exitoso.
        // La heredamos de SimpleUrlLogoutSuccessHandler.
        super.setDefaultTargetUrl("/");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        if (authentication != null) {
            eventoSeguridadService.registrar(
                    authentication.getName(),
                    TipoEventoSeguridad.LOGOUT,
                    null
            );
        }

        // Delega la redirección al comportamiento estándar de la clase
        // padre, que usa la URL fijada en setDefaultTargetUrl ("/").
        super.onLogoutSuccess(request, response, authentication);
    }
}