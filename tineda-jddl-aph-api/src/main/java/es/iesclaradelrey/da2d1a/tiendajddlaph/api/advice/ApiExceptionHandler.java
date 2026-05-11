package es.iesclaradelrey.da2d1a.tiendajddlaph.api.advice;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    public ProblemDetail handleProductoNotFound(ProductoNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problem.setTitle("Producto no encontrado");
        problem.setType(URI.create("https://tienda.local/errors/producto-no-encontrado"));
        return problem;
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ProblemDetail handleStockInsuficiente(StockInsuficienteException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );
        problem.setTitle("Stock insuficiente");
        problem.setType(URI.create("https://tienda.local/errors/stock-insuficiente"));
        return problem;
    }

    @ExceptionHandler(ProductoNoEnCarritoException.class)
    public ProblemDetail handleProductoNoEnCarrito(ProductoNoEnCarritoException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );
        problem.setTitle("Producto no en carrito");
        problem.setType(URI.create("https://tienda.local/errors/producto-no-en-carrito"));
        return problem;
    }

    // Unidades inválidas (≤ 0) — se lanza como IllegalArgumentException desde el controller
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage()
        );
        problem.setTitle("Petición incorrecta");
        problem.setType(URI.create("https://tienda.local/errors/peticion-incorrecta"));
        return problem;
    }

    @ExceptionHandler(MarcaNotFoundException.class)
    public ProblemDetail handleMarcaNotFound(MarcaNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problem.setTitle("Marca no encontrada");
        problem.setType(URI.create("https://tienda.local/errors/marca-no-encontrada"));
        return problem;
    }

    @ExceptionHandler(CategoriaNotFoundException.class)
    public ProblemDetail handleCategoriaNotFound(CategoriaNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problem.setTitle("Categoría no encontrada");
        problem.setType(URI.create("https://tienda.local/errors/categoria-no-encontrada"));
        return problem;
    }
}