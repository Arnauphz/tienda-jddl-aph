package es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(Long id) {
        super("Categoría no encontrada con id: " + id);
    }
}