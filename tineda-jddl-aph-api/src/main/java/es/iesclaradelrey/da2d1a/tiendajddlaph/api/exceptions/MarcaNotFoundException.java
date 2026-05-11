package es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions;

public class MarcaNotFoundException extends RuntimeException {
    public MarcaNotFoundException(Long id) {
        super("Marca no encontrada con id: " + id);
    }
}