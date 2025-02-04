package com.rm.ifood_backend.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantDTO {
  private String name;
  private String email;
  private String password;
  private String cnpj;
  private String address;
  private String category;
  private String phone;
}
