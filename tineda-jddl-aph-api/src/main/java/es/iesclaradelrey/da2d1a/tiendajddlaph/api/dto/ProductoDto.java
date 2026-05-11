package es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductoDto {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private Integer descuento;
    private MarcaDto marca;
    private List<CategoriaDto> categorias;
}