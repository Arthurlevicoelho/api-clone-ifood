package com.rm.ifood_backend.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDTO {

  @NotNull(message = "Nome não pode ser nulo")
  private String name;

  @NotNull(message = "Email não pode ser nulo")
  @Email(message = "Deve ser um email válido")
  private String email;

  private String cpf;

  @NotNull(message = "Senha não pode ser nulo")
  @Min(value = 7, message = "Senha deve ter no mínimo 7 caracteres")
  private String password;

  @NotNull(message = "Endereço não pode ser nulo")
  private String address;

  private String phone;
}