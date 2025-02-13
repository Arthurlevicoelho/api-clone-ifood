package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.model.complement.ComplementDTO;
import com.rm.ifood_backend.model.product.ProductDTO;
import com.rm.ifood_backend.model.product.Product;
import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.model.restaurant.CreateRestaurantDTO;
import com.rm.ifood_backend.model.restaurant.RestaurantResponseDTO;
import com.rm.ifood_backend.model.restaurant.UpdateRestaurantDTO;
import com.rm.ifood_backend.model.restaurant.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {OrderMapper.class})
public interface RestaurantMapper {
  RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

  @Mapping(target = "orders", ignore = true)
  @Mapping(target = "menu", ignore = true)
  Restaurant toEntityFromCreateDto(CreateRestaurantDTO dto);

  @Mapping(target = "orders", ignore = true)
  @Mapping(target = "menu", ignore = true)
  Restaurant toEntityFromUpdateDto(UpdateRestaurantDTO dto);

  @Mapping(source = "menu", target = "menu", qualifiedByName = "mapProductToDto")
  @Mapping(source = "orders", target = "orders")
  RestaurantResponseDTO toResponseDto(Restaurant restaurant);

  @Named("mapProductToDto")
  default List<ProductDTO> mapProductToDto(List<Product> menu) {
    if (menu == null) {
      return new ArrayList<>();
    }
    return menu.stream()
        .map(this::toProductDto)
        .collect(Collectors.toList());
  }

  @Named("mapProductToEntity")
  default List<Product> mapProductEntity(List<ProductDTO> menuDto) {
    if (menuDto == null) {
      return new ArrayList<>();
    }
    return menuDto.stream()
        .map(this::toProductEntity)
        .collect(Collectors.toList());
  }

  default ProductDTO toProductDto(Product product) {
    return ProductDTO.builder()
        .id(product.getId())
        .restaurant_id(
            product.getRestaurant() != null ?
                product.getRestaurant().getId() : null
        )
        .name(product.getName())
        .price(product.getPrice())
        .description(product.getDescription())
        .available(product.isAvailable())
        .complements(mapComplementsToDto(product.getComplements()))
        .build();
  }

  default Product toProductEntity(ProductDTO productDto) {
    return Product.builder()
        .id(productDto.getId())
        .name(productDto.getName())
        .price(productDto.getPrice())
        .description(productDto.getDescription())
        .available(productDto.isAvailable())
        .complements(mapComplementsFromDto(productDto.getComplements()))
        .build();
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
        .collect(Collectors.toList());
  }
}
