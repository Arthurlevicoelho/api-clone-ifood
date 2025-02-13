package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.model.complement.ComplementDTO;
import com.rm.ifood_backend.model.product.CreateProductDTO;
import com.rm.ifood_backend.model.product.ProductResponseDTO;
import com.rm.ifood_backend.model.product.UpdateProductDTO;
import com.rm.ifood_backend.model.product.Product;
import com.rm.ifood_backend.model.restaurant.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(source = "restaurant_id", target = "restaurant", qualifiedByName = "mapRestaurantFromId")
  @Mapping(target = "complements", ignore = true )
  Product toEntityFromCreateDto(CreateProductDTO createProductDTO);

  @Mapping(source = "restaurant_id", target = "restaurant", qualifiedByName = "mapRestaurantFromId")
  @Mapping(target = "complements", ignore = true )
  Product toEntityFromUpdateDto(UpdateProductDTO updateProductDTO);

  @Mapping(source = "restaurant", target = "restaurant_id", qualifiedByName = "mapRestaurantIdFromEntity")
  @Mapping(source = "complements", target = "complements", qualifiedByName = "mapComplementsToDto")
  ProductResponseDTO toResponseDto(Product product);

  @Named("mapRestaurantFromId")
  default Restaurant mapRestaurantFromId(UUID restaurantId) {
    Restaurant restaurant = new Restaurant();
    restaurant.setId(restaurantId);
    return restaurant;
  }

  @Named("mapRestaurantIdFromEntity")
  default UUID mapRestaurantIdFromEntity(Restaurant restaurant) {
    return restaurant != null ? restaurant.getId() : null;
  }

  @Named("mapComplementsFromDto")
  default List<Complement> mapComplementsFromDto(List<ComplementDTO> complementDTOS) {
    if (complementDTOS == null) {
      return new ArrayList<>();
    }
    return complementDTOS.stream()
        .map(complementDTO -> Complement.builder()
            .id(complementDTO.getId())
            .name(complementDTO.getName())
            .price(complementDTO.getPrice())
            .build()).toList();
  }

  @Named("mapComplementsToDto")
  default List<ComplementDTO> mapComplementsToDto(List<Complement> complements) {
    if (complements == null) {
      return new ArrayList<>();
    }
    return complements.stream()
        .map(complement -> ComplementDTO.builder()
            .id(complement.getId())
            .name(complement.getName())
            .price(complement.getPrice())
            .build())
        .toList();
  }
}
