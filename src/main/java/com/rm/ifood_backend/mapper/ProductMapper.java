package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.dto.product.CreateProductDTO;
import com.rm.ifood_backend.dto.product.ProductResponseDTO;
import com.rm.ifood_backend.dto.product.UpdateProductDTO;
import com.rm.ifood_backend.model.Product;
import com.rm.ifood_backend.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(source = "restaurant_id", target = "restaurant", qualifiedByName = "mapRestaurantFromId")
  Product toEntityFromCreateDto(CreateProductDTO createProductDTO);

  @Mapping(source = "restaurant_id", target = "restaurant", qualifiedByName = "mapRestaurantFromId")
  Product toEntityFromUpdateDto(UpdateProductDTO updateProductDTO);

  @Mapping(source = "restaurant", target = "restaurant_id", qualifiedByName = "mapRestaurantIdFromEntity")
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
}