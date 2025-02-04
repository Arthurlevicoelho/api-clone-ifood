package com.rm.ifood_backend.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientDTO {
  private UUID id;
  private String name;
  private String email;
  private String cpf;
  private String password;
  private String address;
  private String phone;
}
