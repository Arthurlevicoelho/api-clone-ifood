package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.model.complement.ComplementResponseDTO;
import com.rm.ifood_backend.model.complement.CreateComplementDTO;
import com.rm.ifood_backend.model.complement.UpdateComplementDTO;
import com.rm.ifood_backend.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface ComplementMapper {

  ComplementMapper INSTANCE = Mappers.getMapper(ComplementMapper.class);

  @Mapping(target = "product", ignore = true)
  Complement toEntityFromCreateDto(CreateComplementDTO createComplementDTO);

  @Mapping(target = "product", ignore = true)
  Complement toEntityFromUpdateDto(UpdateComplementDTO updateComplementDTO);

  @Mapping(source = "product", target = "product_id", qualifiedByName = "mapProductIdFromEntity")
  ComplementResponseDTO toResponseDto(Complement complement);

  @Named("mapProductIdFromEntity")
  default UUID mapProductIdFromEntity(Product product) {
    return product != null ? product.getId() : null;
  }
}
