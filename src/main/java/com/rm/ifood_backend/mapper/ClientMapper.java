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
            .id(orderDTO.id())
            .restaurant(Restaurant.builder().id(orderDTO.restaurant_id()).build())
            .client(Client.builder().id(orderDTO.client_id()).build())
            .products(orderDTO.products().stream()
                .map(this::toProductEntity)
                .collect(Collectors.toList()))
            .total_price(orderDTO.total_price())
            .status(orderDTO.status())
            .build())
        .collect(Collectors.toList());
  }

  @Named("mapOrdersToDto")
  default List<OrderDTO> mapOrdersToDto(List<Order> orders) {
    if (orders == null) {
      return new ArrayList<>();
    }
    return orders.stream()
        .map(order -> new OrderDTO(
            order.getId(),
            order.getClient().getId(),
            order.getRestaurant().getId(),
            mapProductsToDto(order.getProducts()),
            order.getTotal_price(),
            order.getStatus()
        ))
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
    return new ProductDTO(
        product.getId(),
        product.getRestaurant().getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.isAvailable(),
        mapComplementsToDto(product.getComplements())
    );
  }


  private Product toProductEntity(ProductDTO productDto) {
    return Product.builder()
        .id(productDto.id())
        .name(productDto.name())
        .price(productDto.price())
        .description(productDto.description())
        .available(productDto.available())
        .complements(mapComplementsFromDto(productDto.complements()))
        .build();
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
