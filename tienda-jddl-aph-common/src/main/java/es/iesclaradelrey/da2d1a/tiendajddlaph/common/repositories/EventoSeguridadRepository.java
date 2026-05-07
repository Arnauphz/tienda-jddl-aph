package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.EventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.TipoEventoSeguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoSeguridadRepository extends JpaRepository<EventoSeguridad, Long> {

    // Consultas que pueden ser útiles en el futuro (panel de admin, etc.)
    List<EventoSeguridad> findByNombreUsuario(String nombreUsuario);
    List<EventoSeguridad> findByTipo(TipoEventoSeguridad tipo);
}