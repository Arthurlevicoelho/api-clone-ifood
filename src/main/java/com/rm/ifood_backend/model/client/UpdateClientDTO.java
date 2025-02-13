package com.rm.ifood_backend.model.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

  @Email(message = "Deve ser um email válido")
  private String email;

  @Pattern(regexp = "^(?=.*[^a-zA-Z0-9]).{8,}$", message = "Senha deve conter no mínimo 8 caracteres e 1 caractere especial")
  private String password;

  @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter 11 números")
  private String cpf;

  private String address;

  @Pattern(regexp = "^[0-9]{11}$", message = "Número de telefone deve conter 11 digitos, incluindo o DDD")
  private String phone;
}
