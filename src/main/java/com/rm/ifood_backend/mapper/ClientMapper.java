package com.rm.ifood_backend.mapper;

import com.rm.ifood_backend.model.client.ClientResponseDTO;
import com.rm.ifood_backend.model.client.CreateClientDTO;
import com.rm.ifood_backend.model.client.UpdateClientDTO;
import com.rm.ifood_backend.model.complement.ComplementDTO;
import com.rm.ifood_backend.model.product.ProductDTO;
import com.rm.ifood_backend.model.product.Product;
import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.model.order.OrderDTO;
import com.rm.ifood_backend.model.order.Order;
import com.rm.ifood_backend.model.client.Client;
import com.rm.ifood_backend.model.restaurant.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    if (orderDTOs == null) {
      return new ArrayList<>();
    }
    return orderDTOs.stream()
        .map(orderDTO -> Order.builder()
            .id(orderDTO.getId())
            .restaurant(Restaurant.builder().id(orderDTO.getRestaurant_id()).build())
            .client(Client.builder().id(orderDTO.getClient_id()).build())
            .products(orderDTO.getProducts().stream()
                .map(this::toProductEntity)
                .collect(Collectors.toList()))
            .total_price(orderDTO.getTotal_price())
            .status(orderDTO.getStatus())
            .build())
        .collect(Collectors.toList());
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
        .collect(Collectors.toList());
  }

  private List<ProductDTO> mapProductsToDto(List<Product> products) {
    if (products == null) {
      return new ArrayList<>();
    }
    return products.stream()
        .map(this::toProductDto)
        .collect(Collectors.toList());
  }

  private ProductDTO toProductDto(Product product) {
    return ProductDTO.builder()
        .id(product.getId())
        .restaurant_id(product.getRestaurant() != null ? product.getRestaurant().getId() : null)
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .available(product.isAvailable())
        .complements(mapComplementsToDto(product.getComplements()))
        .build();
  }

  private Product toProductEntity(ProductDTO productDto) {
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
        .toList();
  }
}
