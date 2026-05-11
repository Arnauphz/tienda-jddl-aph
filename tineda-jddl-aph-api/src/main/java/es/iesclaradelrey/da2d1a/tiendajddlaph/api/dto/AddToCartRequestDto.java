package es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long productoId;
    private Integer unidades;
}