package es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions;

public class ProductoNotFoundException extends RuntimeException {
    private final Long productoId;

    public ProductoNotFoundException(Long productoId) {
        super("Producto no encontrado con id: " + productoId);
        this.productoId = productoId;
    }

    public Long getProductoId() {
        return productoId;
    }
}