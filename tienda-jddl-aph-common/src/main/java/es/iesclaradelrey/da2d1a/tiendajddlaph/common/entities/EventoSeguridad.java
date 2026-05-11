package es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evento_seguridad")
public class EventoSeguridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha y hora exacta en que ocurrió el evento.
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Nombre de usuario (email) involucrado en el evento.
     * Se guarda como String suelto, sin FK a Usuario, porque
     * en un login fallido el usuario puede no existir en BD.
     */
    @Column(name = "nombre_usuario", nullable = false, length = 200)
    private String nombreUsuario;

    /**
     * Tipo de evento: LOGIN_OK, LOGIN_ERROR o LOGOUT.
     * Se persiste como String (EnumType.STRING) para que la BD
     * sea legible sin necesidad de traducir números a significados.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoEventoSeguridad tipo;

    /**
     * Información adicional opcional: IP del cliente, mensaje de error, etc.
     */
    @Column(length = 300)
    private String detalle;
}
