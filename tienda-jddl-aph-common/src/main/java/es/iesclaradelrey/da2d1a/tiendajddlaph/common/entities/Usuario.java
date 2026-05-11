package es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Email del usuario. Se usa también como nombre de usuario para login.
     * Único en la base de datos: no puede haber dos cuentas con el mismo email.
     */
    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String apellidos;

    /**
     * Teléfono. Opcional en la BD según indica el enunciado.
     */
    @Column(length = 20)
    private String telefono;

    /**
     * Fecha de nacimiento. Opcional en la BD según indica el enunciado.
     */
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    /**
     * Contraseña codificada con bcrypt.
     */
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * Fecha y hora del registro del usuario en el sistema.
     * Lo asignará automáticamente el servidor en el momento del registro.
     */
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Roles asignados al usuario.
     *
     * Es un Set (no List) porque:
     *  - No puede haber roles duplicados para un mismo usuario.
     *  - Un Set captura la semántica de "conjunto" sin necesidad de
     *    duplicar la lógica anti-duplicados en código de aplicación.
     *
     * @ManyToMany porque un usuario tiene varios roles, y un mismo
     * rol puede estar asignado a muchos usuarios.
     *
     * fetch = EAGER: cuando cargamos el usuario para el login, queremos
     * que sus roles vengan ya. Con LAZY tendríamos LazyInitializationException
     * en la sesión de Spring Security, donde no hay sesión Hibernate abierta.
     *
     * Esta entidad es la "owning side" de la relación: define la tabla
     * intermedia con @JoinTable. La entidad Rol no necesita tener una
     * referencia inversa: el flujo natural es de usuario a rol, no al revés.
     */
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();
}