package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.dto.product.ProductDTO;
import com.rm.ifood_backend.dto.restaurant.CreateRestaurantDTO;
import com.rm.ifood_backend.dto.restaurant.RestaurantResponseDTO;
import com.rm.ifood_backend.dto.restaurant.UpdateRestaurantDTO;
import com.rm.ifood_backend.model.Product;
import com.rm.ifood_backend.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

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
        .toList();
  }


  @Named("mapProductToEntity")
  default List<Product> mapProductEntity(List<ProductDTO> menuDto) {
    return menuDto.stream().map(this::toProductEntity).toList();
  }

  default ProductDTO toProductDto(Product product) {
    return ProductDTO.builder()
        .id(product.getId())
        .restaurant_id(
            product.getRestaurant() != null ?
                product.getRestaurant().getId() : null // Handle null restaurant
        )
        .name(product.getName())
        .price(product.getPrice())
        .description(product.getDescription())
        .available(product.isAvailable())
        .build();
  }

  default Product toProductEntity(ProductDTO productDto) {
    return Product.builder()
        .id(productDto.getId())
        .name(productDto.getName())
        .price(productDto.getPrice())
        .description(productDto.getDescription())
        .available(productDto.isAvailable())
        .build();
  }
}