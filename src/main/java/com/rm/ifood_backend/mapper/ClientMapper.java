package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.dto.client.ClientResponseDTO;
import com.rm.ifood_backend.dto.client.CreateClientDTO;
import com.rm.ifood_backend.dto.client.UpdateClientDTO;
import com.rm.ifood_backend.dto.product.ProductDTO;
import com.rm.ifood_backend.dto.order.OrderDTO;
import com.rm.ifood_backend.model.Client;
import com.rm.ifood_backend.model.Product;
import com.rm.ifood_backend.model.Order;
import com.rm.ifood_backend.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ClientMapper {

  ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

  @Mapping(target = "orders", ignore = true)
  Client toEntityFromCreateDto(CreateClientDTO createClientDTO);

  @Mapping(target = "orders", ignore = true)
  Client toEntityFromUpdateDto(UpdateClientDTO updateClientDTO);

  @Mapping(source = "orders", target = "orders", qualifiedByName = "mapOrdersToDto")
  ClientResponseDTO toResponseDTO(Client client);

  @Named("mapOrdersFromDto")
  default List<Order> mapOrdersFromDto(List<OrderDTO> orderDTOs) {
    return orderDTOs.stream()
        .map(orderDTO -> Order.builder()
            .id(orderDTO.getId())
            .restaurant(Restaurant.builder().id(orderDTO.getRestaurant_id()).build())
            .client(Client.builder().id(orderDTO.getClient_id()).build())
            .products(orderDTO.getProducts().stream()
                .map(productDto -> Product.builder()
                    .id(productDto.getId())
                    .name(productDto.getName())
                    .description(productDto.getDescription())
                    .price(productDto.getPrice())
                    .available(productDto.isAvailable())
                    .build())
                .toList())
            .total_price(orderDTO.getTotal_price())
            .status(orderDTO.getStatus())
            .build())
        .toList();
  }

  @Named("mapOrdersToDto")
  default List<OrderDTO> mapOrdersToDto(List<Order> orders) {
    if (orders == null) {
      return new ArrayList<>();
    }
    return orders.stream()
        .map(order -> OrderDTO.builder()
            .id(order.getId())
            .restaurant_id(order.getRestaurant() != null ? order.getRestaurant().getId() : null)
            .client_id(order.getClient() != null ? order.getClient().getId() : null)
            .products(mapProductsToDto(order.getProducts()))
            .total_price(order.getTotal_price())
            .status(order.getStatus())
            .build())
        .toList();
  }

  private List<ProductDTO> mapProductsToDto(List<Product> products) {
    if (products == null) {
      return new ArrayList<>();
    }
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
