package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.EventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.TipoEventoSeguridad;

public interface EventoSeguridadService {

    EventoSeguridad registrar(String nombreUsuario, TipoEventoSeguridad tipo, String detalle);
}