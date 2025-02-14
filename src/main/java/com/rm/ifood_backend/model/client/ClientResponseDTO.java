package com.rm.ifood_backend.model.client;

import com.rm.ifood_backend.model.order.OrderDTO;
import java.util.List;
import java.util.UUID;


public record ClientResponseDTO (

  UUID id,
  String name,
  String email,
  String cpf,
  String address,
  String phone,
  List<OrderDTO> orders
){
  public ClientResponseDTO{
    orders = orders != null ? List.copyOf(orders) : List.of();
  }
}