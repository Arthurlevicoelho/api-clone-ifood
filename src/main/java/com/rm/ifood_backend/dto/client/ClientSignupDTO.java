package com.rm.ifood_backend.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSignupDTO {
  private String name;
  private String email;
  private String password;
  private String cpf;
  private String phone;
  private String address;
}
