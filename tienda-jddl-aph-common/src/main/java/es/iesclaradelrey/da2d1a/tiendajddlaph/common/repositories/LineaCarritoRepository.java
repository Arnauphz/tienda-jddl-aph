package es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.LineaCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LineaCarritoRepository extends JpaRepository<LineaCarrito, Long> {

    // Todas las líneas del carrito de un usuario
    List<LineaCarrito> findByUsuarioEmail(String email);

    // Una línea concreta: usuario + producto
    Optional<LineaCarrito> findByUsuarioEmailAndProductoId(String email, Long productoId);

    // Número de productos distintos en el carrito de un usuario
    @Query("SELECT COUNT(lc) FROM LineaCarrito lc WHERE lc.usuario.email = :email")
    Long countProductosByUsuarioEmail(@Param("email") String email);

    // Total de unidades en el carrito de un usuario
    @Query("SELECT COALESCE(SUM(lc.unidades), 0) FROM LineaCarrito lc WHERE lc.usuario.email = :email")
    Long sumUnidadesByUsuarioEmail(@Param("email") String email);

    // Importe total del carrito (precio * (1 - descuento/100) * unidades)
    @Query("""
        SELECT COALESCE(SUM(lc.producto.precio * (1 - lc.producto.descuento / 100.0) * lc.unidades), 0)
        FROM LineaCarrito lc
        WHERE lc.usuario.email = :email
        """)
    Double sumImporteTotalByUsuarioEmail(@Param("email") String email);

    // Eliminar todas las líneas de un usuario (vaciar carrito)
    void deleteByUsuarioEmail(String email);
}