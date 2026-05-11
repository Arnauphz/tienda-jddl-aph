package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

/**
 * Se lanza cuando se intenta registrar un usuario con un email que
 * ya existe en la base de datos.
 *
 * Es una RuntimeException por simplicidad: no obligamos al caller
 * a declarar throws en su firma. El controlador la captura
 * explícitamente para mostrar el error en el formulario.
 */
public class EmailYaRegistradoException extends RuntimeException {

    public EmailYaRegistradoException(String email) {
        super("Ya existe un usuario registrado con el email: " + email);
    }
}
