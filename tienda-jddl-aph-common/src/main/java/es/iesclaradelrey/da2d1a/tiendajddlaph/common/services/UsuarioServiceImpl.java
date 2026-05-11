package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario registrar(String email,
                             String nombre,
                             String apellidos,
                             String telefono,
                             LocalDate fechaNacimiento,
                             String passwordPlano) {

        // 1. Validar que el email no esté ya en uso.
        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailYaRegistradoException(email);
        }

        // 2. Construir la entidad. La contraseña se cifra con el mismo
        //    PasswordEncoder (BCrypt cost 12) que usa el login para verificar.
        Usuario usuario = Usuario.builder()
                .email(email)
                .nombre(nombre)
                .apellidos(apellidos)
                .telefono(telefono)
                .fechaNacimiento(fechaNacimiento)
                .password(passwordEncoder.encode(passwordPlano))
                .fechaRegistro(LocalDateTime.now())
                .build();

        // 3. Persistir y devolver. JPA asignará el id al hacer flush.
        return usuarioRepository.save(usuario);
    }
}