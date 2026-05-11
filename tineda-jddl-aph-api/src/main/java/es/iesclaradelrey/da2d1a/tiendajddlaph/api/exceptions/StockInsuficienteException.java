package es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(Long productoId, int stockDisponible, int unidadesSolicitadas) {
        super("Stock insuficiente para el producto " + productoId
                + ". Disponible: " + stockDisponible
                + ", solicitadas: " + unidadesSolicitadas);
    }
}