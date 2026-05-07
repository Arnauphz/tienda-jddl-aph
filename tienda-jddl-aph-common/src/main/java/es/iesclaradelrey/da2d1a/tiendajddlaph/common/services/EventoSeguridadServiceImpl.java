package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.EventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.EventoSeguridadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventoSeguridadServiceImpl implements EventoSeguridadService {

    private final EventoSeguridadRepository eventoSeguridadRepository;

    public EventoSeguridadServiceImpl(EventoSeguridadRepository eventoSeguridadRepository) {
        this.eventoSeguridadRepository = eventoSeguridadRepository;
    }

    @Override
    public EventoSeguridad registrar(String nombreUsuario, TipoEventoSeguridad tipo, String detalle) {
        EventoSeguridad evento = EventoSeguridad.builder()
                .fechaHora(LocalDateTime.now())
                .nombreUsuario(nombreUsuario)
                .tipo(tipo)
                .detalle(detalle)
                .build();
        return eventoSeguridadRepository.save(evento);
    }
}