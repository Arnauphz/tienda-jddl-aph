package es.iesclaradelrey.da2d1a.tiendajddlaph.security.listeners;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.services.EventoSeguridadService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

/**
 * Escucha el evento que Spring Security publica cuando un intento de login
 * falla (credenciales incorrectas, usuario no encontrado, cuenta bloqueada...)
 * y registra un EventoSeguridad de tipo LOGIN_ERROR.
 *
 * Usamos AbstractAuthenticationFailureEvent en lugar de una subclase concreta
 * para capturar cualquier tipo de fallo, sea cual sea la causa.
 */
@Component
public class LoginErrorListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    private final EventoSeguridadService eventoSeguridadService;

    public LoginErrorListener(EventoSeguridadService eventoSeguridadService) {
        this.eventoSeguridadService = eventoSeguridadService;
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        String nombreUsuario = event.getAuthentication().getName();
        String motivo = event.getException().getMessage();
        eventoSeguridadService.registrar(nombreUsuario, TipoEventoSeguridad.LOGIN_ERROR, motivo);
    }
}