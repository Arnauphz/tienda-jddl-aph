package es.iesclaradelrey.da2d1a.tiendajddlaph.api.mappers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.MarcaDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Marca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    MarcaDto toDto(Marca marca);
}