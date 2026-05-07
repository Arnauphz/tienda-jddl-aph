package es.iesclaradelrey.da2d1a.tiendajddlaph.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * Configuración de seguridad HTTP para el módulo web.
 *
 * Define el SecurityFilterChain que sustituye al SecurityFilterChain
 * por defecto de Spring Boot. Aquí están las reglas específicas de
 * autorización de la tienda y del área de administración, así como
 * los ajustes de CSRF, frames, login y Basic Auth.
 *
 * El PasswordEncoder y el UserDetailsService NO se configuran aquí: son
 * beans del módulo "security" y Spring Security los recoge automáticamente
 * del contenedor IoC.
 */
@Configuration
@EnableMethodSecurity
public class ConfiguracionSeguridad {

    /**
     * Ruta configurada para la consola de H2. Por defecto en Spring Boot
     * es "/h2-console", pero la leemos de la configuración para no
     * hardcodearla y mantener coherencia con application.properties.
     */
    @Value("${spring.h2.console.path:/h2-console}")
    private String rutaConsolaH2;

    /**
     * Handler que se ejecuta cuando el logout tiene éxito.
     *
     * Lo inyectamos por la interfaz (LogoutSuccessHandler), no por la
     * implementación concreta. De esta forma el módulo web no necesita
     * conocer la clase específica del módulo security: Spring se encarga
     * de inyectar el bean adecuado.
     */
    private final LogoutSuccessHandler logoutSuccessHandler;

    public ConfiguracionSeguridad(LogoutSuccessHandler logoutSuccessHandler) {
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Construimos un patrón con sus subrutas, p.ej. "/h2-console/**"
        String patronConsolaH2 = rutaConsolaH2 + "/**";

        http
                // ── 1. Reglas de autorización por URL ──────────────────────
                .authorizeHttpRequests(auth -> auth
                        // /h2-console y todas sus subrutas: solo autenticados.
                        .requestMatchers(rutaConsolaH2, patronConsolaH2).authenticated()

                        // Área de administración: solo usuarios con rol ADMIN.
                        .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")

                        // Perfil de usuario: solo accesible si autenticado.
                        // Si un usuario anónimo intenta acceder, Spring Security
                        // lo redirige automáticamente a /login.
                        .requestMatchers("/users/profile", "/users/profile/**").authenticated()

                        // Registro de usuarios y página de login:
                        // SOLO accesibles si NO hay sesión activa. Un usuario
                        // autenticado que intente entrar a /register o /login
                        // recibirá un HTTP 403 Forbidden.
                        .requestMatchers("/register", "/login").anonymous()

                        // Resto de la tienda: libre acceso.
                        // Esto incluye páginas públicas, recursos estáticos
                        // (CSS, JS, imágenes), páginas de error, etc.
                        .anyRequest().permitAll()
                )

                // ── 2. CSRF ────────────────────────────────────────────────
                // Mantenemos la protección CSRF activa de forma global
                // (necesaria en formularios POST), pero la desactivamos solo
                // para /h2-console, ya que esa consola interna no la soporta.
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(rutaConsolaH2, patronConsolaH2)
                )

                // ── 3. Cabecera X-Frame-Options ────────────────────────────
                // Por defecto Spring Security la pone en DENY, lo que
                // impediría que la consola de H2 (que usa frames internos)
                // se mostrase. Cambiamos a SAMEORIGIN para permitir frames
                // del mismo origen, sin abrir la app a clickjacking externo.
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )

                /// ── 4. Esquema de autenticación: formulario personalizado ──
                // loginPage("/login"): Spring Security deja de generar su formulario
                // por defecto y delega en nuestro controlador para servir la vista.
                // Tras un fallo, Spring redirige a /login?error (es el comportamiento
                // estándar; lo aprovechamos para mostrar el mensaje en la plantilla).
                .formLogin(form -> form
                        .loginPage("/login")
                )

                // ── 5. Desactivar HTTP Basic ───────────────────────────────
                // No queremos que las peticiones puedan autenticarse con
                // cabecera Basic. La tienda solo debe usar el formulario.
                .httpBasic(basic -> basic.disable())

                // ── 6. Logout ──────────────────────────────────────────────
                // Usamos un LogoutSuccessHandler propio para registrar el
                // evento en BD y gestionar la redirección. La URL de destino
                // se configurará en el punto 3.4 del PDF 11.
                .logout(logout -> logout
                        .logoutSuccessHandler(logoutSuccessHandler)
                );

        return http.build();
    }
}
