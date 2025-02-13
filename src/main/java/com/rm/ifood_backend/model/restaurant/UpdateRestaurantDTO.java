package com.rm.ifood_backend.model.restaurant;

import com.rm.ifood_backend.model.product.ProductDTO;
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
public class UpdateRestaurantDTO {
  private UUID id;

  private String name;

  private String email;

  private String password;

  private String cnpj;

  private String phone;

  private String address;

  private String category;

  private List<ProductDTO> menu = new ArrayList<>();
}
