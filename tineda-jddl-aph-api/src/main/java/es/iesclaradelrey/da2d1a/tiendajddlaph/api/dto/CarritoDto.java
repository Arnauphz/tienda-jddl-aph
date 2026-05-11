package es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarritoDto {
    private List<LineaCarritoDto> productos;
    private Long numProductosDistintos;
    private Long numUnidadesTotales;
    private Double importeTotal;
}