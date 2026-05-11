package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Consulta derivada por categoría con Sort como parámetro (sin OrderBy en el nombre)
    List<Producto> findByCategorias_Id(Long categoriaId, Sort sort);
}