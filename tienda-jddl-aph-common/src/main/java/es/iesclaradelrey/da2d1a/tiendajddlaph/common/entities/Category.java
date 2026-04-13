package es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Category {

    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;

}
