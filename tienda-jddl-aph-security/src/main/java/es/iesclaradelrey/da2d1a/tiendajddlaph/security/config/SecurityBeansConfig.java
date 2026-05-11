package es.iesclaradelrey.da2d1a.tiendajddlaph.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Beans de seguridad comunes a todos los módulos que dependen de "security".
 *
 * Aquí van componentes que NO son específicos del flujo HTTP (eso va en
 * el SecurityFilterChain del módulo web). El PasswordEncoder, por ejemplo,
 * lo usará tanto el formulario de registro (web) como, en su día, los
 * servicios de la API REST.
 */
@Configuration
public class SecurityBeansConfig {

    /**
     * Encoder de contraseñas usado por toda la aplicación.
     *
     * Se usa BCrypt con cost factor 12 (2^12 = 4096 iteraciones internas),
     * por encima del 10 por defecto de Spring. A mayor cost, más lento es
     * calcular el hash y, por tanto, más caros los ataques de fuerza bruta
     * o por diccionario contra una BD comprometida.
     *
     * El mismo bean se utiliza para:
     *   - Codificar contraseñas al registrar nuevos usuarios.
     *   - Verificar contraseñas al hacer login (Spring Security lo invoca
     *     automáticamente desde el DaoAuthenticationProvider).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}