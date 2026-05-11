package es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto;

import lombok.Data;

@Data
public class LineaCarritoDto {
    private String nombre;
    private Double precioUnitario;
    private Integer descuento;
    private Double precioConDescuento;
    private Integer unidades;
    private Double precioTotal;
}