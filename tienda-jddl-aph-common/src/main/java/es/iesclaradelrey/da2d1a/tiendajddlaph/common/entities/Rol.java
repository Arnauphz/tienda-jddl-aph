package es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Rol asignable a un usuario para el control de acceso (RBAC).
 *
 * El identificador es una cadena corta (p. ej. "ADMIN", "USER") que
 * coincide con el nombre lógico del rol. Lo asignamos manualmente,
 * NO es identity: queremos que los ids sean predecibles para usarlos
 * en data.sql, en SecurityFilterChain y en anotaciones como
 * @PreAuthorize("hasRole('ADMIN')").
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol")
public class Rol {

    /**
     * Identificador del rol. Ejemplos: "ADMIN", "USER".
     * Máximo 6 caracteres según el enunciado.
     */
    @Id
    @Column(length = 6)
    private String id;

    /**
     * Descripción legible del rol. Se muestra al usuario en pantallas
     * de administración o de perfil.
     */
    @Column(nullable = false, length = 100)
    private String descripcion;
}