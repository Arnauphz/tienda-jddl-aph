package es.iesclaradelrey.da2d1a.tiendajddlaph.security.listeners;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.EventoSeguridadService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Escucha el evento que Spring Security publica cuando un usuario
 * completa el login con éxito y registra un EventoSeguridad de tipo LOGIN_OK.
 */
@Component
public class LoginCorrectoListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final EventoSeguridadService eventoSeguridadService;

    public LoginCorrectoListener(EventoSeguridadService eventoSeguridadService) {
        this.eventoSeguridadService = eventoSeguridadService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String nombreUsuario = event.getAuthentication().getName();
        eventoSeguridadService.registrar(nombreUsuario, TipoEventoSeguridad.LOGIN_OK, null);
    }
}