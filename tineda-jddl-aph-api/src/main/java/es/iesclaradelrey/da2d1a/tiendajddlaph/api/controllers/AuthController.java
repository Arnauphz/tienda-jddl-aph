package es.iesclaradelrey.da2d1a.tiendajddlaph.api.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.LoginRequestDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.LoginResponseDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.RefreshRequestDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    // POST /api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return ResponseEntity.ok(new LoginResponseDto(accessToken, refreshToken));
    }

    // POST /api/v1/auth/refresh
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequestDto request) {
        try {
            String token = request.getRefreshToken();
            String username = jwtService.extractUsername(token);
            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!jwtService.isTokenValid(token, user)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            String newAccessToken = jwtService.generateAccessToken(user);
            return ResponseEntity.ok(new LoginResponseDto(newAccessToken, token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}