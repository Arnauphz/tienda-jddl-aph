package es.iesclaradelrey.da2d1a.tiendajddlaph.security;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Adaptador que envuelve una entidad Usuario y la presenta a Spring Security
 * como UserDetails.
 *
 * Mantenemos esta clase fuera del módulo "common" porque importa Spring
 * Security, y "common" no puede acoplarse a la capa de seguridad.
 *
 * Nota sobre los nombres en inglés de los métodos:
 * Los métodos marcados con @Override (getAuthorities, getPassword,
 * getUsername, isAccountNonExpired, isAccountNonLocked,
 * isCredentialsNonExpired, isEnabled) provienen de la interfaz
 * UserDetails de Spring Security, así que sus nombres son obligatorios.
 *
 * Decisiones de diseño:
 *  - getUsername() devuelve el email, ya que es lo que usamos como
 *    identificador de login en la aplicación.
 *  - getAuthorities() devuelve los roles del usuario con prefijo "ROLE_"
 *    (ver más abajo).
 *  - Las cuatro banderas de estado se devuelven como true: por ahora no
 *    gestionamos cuentas bloqueadas, expiradas, etc.
 */
@Getter
public class MiUsuarioDetails implements UserDetails {

    /**
     * Prefijo que Spring Security añade internamente al evaluar hasRole().
     * Si un rol en BD se llama "ADMIN", debemos exponerlo como "ROLE_ADMIN"
     * para que hasRole('ADMIN') lo reconozca.
     */
    private static final String PREFIJO_ROL = "ROLE_";

    private final Usuario usuario;

    public MiUsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve las authorities del usuario.
     *
     * Cada Rol del usuario se convierte en una SimpleGrantedAuthority
     * con el formato "ROLE_<id>", para que sea compatible con la API
     * hasRole() / hasAnyRole() de Spring Security.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(PREFIJO_ROL + rol.getId()))
                .collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}