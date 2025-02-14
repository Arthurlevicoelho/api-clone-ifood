package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.model.complement.ComplementDTO;
import com.rm.ifood_backend.model.product.ProductDTO;
import com.rm.ifood_backend.model.product.Product;
import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.model.order.CreateOrderDTO;
import com.rm.ifood_backend.model.order.OrderResponseDTO;
import com.rm.ifood_backend.model.order.UpdateOrderDTO;
import com.rm.ifood_backend.model.order.Order;
import com.rm.ifood_backend.model.client.Client;
import com.rm.ifood_backend.model.restaurant.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
          product.setId(productDTO.id());
          product.setName(productDTO.name());
          product.setDescription(productDTO.description());
          product.setPrice(productDTO.price());
          product.setAvailable(productDTO.available());
          product.setRestaurant(new Restaurant());
          product.getRestaurant().setId(productDTO.restaurant_id());

          product.setComplements(productDTO.complements().stream()
              .map(complementDTO -> {
                Complement complement = new Complement();
                complement.setId(complementDTO.id());
                complement.setName(complementDTO.name());
                complement.setPrice(complementDTO.price());
                return complement;
              }).toList());

          return product;
        }).toList();
  }

  @Named("mapProductsToDto")
  default List<ProductDTO> mapProductsToDto(List<Product> products) {
    return products.stream()
        .map(product -> new ProductDTO(
            product.getId(),
            product.getRestaurant().getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.isAvailable(),
            mapComplementsToDto(product.getComplements())
        ))
        .collect(Collectors.toList());
  }

  @Named("mapComplementsFromDto")
  default List<Complement> mapComplementsFromDto(List<ComplementDTO> complementDTOS) {
    if (complementDTOS == null) {
      return new ArrayList<>();
    }
    return complementDTOS.stream()
        .map(complementDTO -> Complement.builder()
            .id(complementDTO.id())
            .name(complementDTO.name())
            .price(complementDTO.price())
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
