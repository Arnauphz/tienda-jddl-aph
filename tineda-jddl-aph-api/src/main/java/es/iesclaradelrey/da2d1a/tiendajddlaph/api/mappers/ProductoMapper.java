package es.iesclaradelrey.da2d1a.tiendajddlaph.api.mappers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class, MarcaMapper.class})
public interface ProductoMapper {

    ProductoDto toDto(Producto producto);

    List<ProductoDto> toDtoList(List<Producto> productos);
}