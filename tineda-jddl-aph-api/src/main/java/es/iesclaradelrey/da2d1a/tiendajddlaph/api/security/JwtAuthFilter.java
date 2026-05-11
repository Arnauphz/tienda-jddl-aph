package es.iesclaradelrey.da2d1a.tiendajddlaph.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 1. Si ya hay un usuario autenticado, no hacemos nada
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 2. Comprobamos que existe la cabecera Authorization
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 3. Verificamos que empieza con "Bearer "
            if (!authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 4. Extraemos el token
            String token = authHeader.substring(7);

            // 5. Extraemos el username (lanza excepción si el token fue manipulado)
            String username = jwtService.extractUsername(token);

            // 6. Cargamos el usuario
            UserDetails user = userDetailsService.loadUserByUsername(username);

            // 7. Validamos el token
            if (!jwtService.isTokenValid(token, user)) {
                filterChain.doFilter(request, response);
                return;
            }

            // 8. Creamos el objeto Authentication y lo fijamos en el SecurityContext
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities()
                    );
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            // Si cualquier cosa falla, pasamos al siguiente filtro sin autenticar
        }

        filterChain.doFilter(request, response);
    }
}