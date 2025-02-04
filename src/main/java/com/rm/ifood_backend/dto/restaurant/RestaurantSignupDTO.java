package com.rm.ifood_backend.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantSignupDTO {
  private String name;
  private String email;
  private String password;
  private String category;
  private String cnpj;
  private String address;
  private String phone;
}
