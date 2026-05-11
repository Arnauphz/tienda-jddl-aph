package es.iesclaradelrey.da2d1a.tiendajddlaph.api.mappers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDto toDto(Categoria categoria);

    List<CategoriaDto> toDtoList(List<Categoria> categorias);
}