package es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(
        name = "producto"
)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    private String codigo;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(length = 50)
    private String marca;

    @Column(nullable = false, length = 4000)
    private String descripcion;

    @Column(length = 500)
    private String imagen;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer descuento;
}