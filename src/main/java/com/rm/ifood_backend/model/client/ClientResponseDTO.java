package com.rm.ifood_backend.model.client;

import com.rm.ifood_backend.model.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {
  private UUID id;
  private String name;
  private String email;
  private String cpf;
  private String address;
  private String phone;
  private List<OrderDTO> orders = new ArrayList<>();
}