package com.rm.ifood_backend.dto.restaurant;

import com.rm.ifood_backend.dto.product.ProductDTO;
import com.rm.ifood_backend.dto.order.OrderDTO;
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
public class RestaurantResponseDTO {
  private UUID id;
  private String name;
  private String email;
  private String password;
  private String cnpj;
  private String address;
  private String category;
  private String phone;
  private List<ProductDTO> menu = new ArrayList<>();
  private List<OrderDTO> orders = new ArrayList<>();
}
