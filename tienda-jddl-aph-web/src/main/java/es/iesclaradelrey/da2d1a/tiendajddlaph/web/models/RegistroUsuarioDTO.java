package es.iesclaradelrey.da2d1a.tiendajddlaph.web.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO para el formulario de registro de nuevos usuarios.
 *
 * Contiene SOLO los campos que el usuario rellena en el formulario.
 * Los datos asignados por el servidor (id, fechaRegistro) NO viajan
 * por el DTO; se establecen en la capa de servicio durante el registro.
 *
 * El campo password está aquí en texto plano. La capa de servicio se
 * encarga de codificarlo con BCrypt antes de persistirlo en BD.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroUsuarioDTO {

    private String email;

    private String nombre;

    private String apellidos;

    /** Opcional. */
    private String telefono;

    /** Opcional. */
    private LocalDate fechaNacimiento;

    /** En texto plano; el servicio lo codificará antes de persistir. */
    private String password;

    /**
     * Indica si el usuario ha marcado la casilla "Acepto condiciones".
     * No es un campo persistente: se valida y se descarta tras el registro.
     */
    private boolean aceptaCondiciones;
}
