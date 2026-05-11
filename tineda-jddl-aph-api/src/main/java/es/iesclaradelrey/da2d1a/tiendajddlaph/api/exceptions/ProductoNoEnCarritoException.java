package es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions;

public class ProductoNoEnCarritoException extends RuntimeException {
    public ProductoNoEnCarritoException(Long productoId) {
        super("El producto con id " + productoId + " no está en el carrito.");
    }
}