package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.dto.product.ProductDTO;
import com.rm.ifood_backend.dto.order.CreateOrderDTO;
import com.rm.ifood_backend.dto.order.OrderResponseDTO;
import com.rm.ifood_backend.dto.order.UpdateOrderDTO;
import com.rm.ifood_backend.model.Product;
import com.rm.ifood_backend.model.Order;
import com.rm.ifood_backend.model.Client;
import com.rm.ifood_backend.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper
public interface OrderMapper {

  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


  @Mapping(target = "total_price", ignore = true)
  @Mapping(source = "restaurant_id", target = "restaurant.id")
  @Mapping(source = "client_id", target = "client.id")
  @Mapping(source = "products", target = "products", qualifiedByName = "mapProductsFromDto")
  Order toEntityFromCreateDto(CreateOrderDTO dto);

  @Mapping(target = "total_price", ignore = true)
  @Mapping(source = "restaurant_id", target = "restaurant.id")
  @Mapping(source = "client_id", target = "client.id")
  @Mapping(source = "products", target = "products", qualifiedByName = "mapProductsFromDto")
  Order toEntityFromUpdateDto(UpdateOrderDTO dto);

  @Mapping(source = "restaurant", target = "restaurant_id", qualifiedByName = "mapRestaurantIdFromEntity")
  @Mapping(source = "client", target = "client_id", qualifiedByName = "mapClientIdFromEntity")
  @Mapping(source = "products", target = "products", qualifiedByName = "mapProductsToDto")
  OrderResponseDTO toResponseDto(Order order);

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

  @Named("mapClientFromId")
  default Client mapClientFromId(UUID clientId) {
    Client client = new Client();
    client.setId(clientId);
    return client;
  }

  @Named("mapClientIdFromEntity")
  default UUID mapClientIdFromEntity(Client client) {
    return client != null ? client.getId() : null;
  }

  @Named("mapProductsFromDto")
  default List<Product> mapProductsFromDto(List<ProductDTO> productDTOS) {
    return productDTOS.stream()
        .map(productDTO -> {
          Product product = new Product();
          product.setId(productDTO.getId());
          product.setName(productDTO.getName());
          product.setDescription(productDTO.getDescription());
          product.setPrice(productDTO.getPrice());
          product.setAvailable(productDTO.isAvailable());
          product.setRestaurant(new Restaurant());
          product.getRestaurant().setId(productDTO.getRestaurant_id());
          return product;
        }).toList();
  }

  @Named("mapProductsToDto")
  default List<ProductDTO> mapProductsToDto(List<Product> products) {
    return products.stream()
        .map(product -> ProductDTO.builder()
            .id(product.getId())
            .restaurant_id(product.getRestaurant() != null ? product.getRestaurant().getId() : null)
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .available(product.isAvailable())
            .build())
        .toList();
  }
}