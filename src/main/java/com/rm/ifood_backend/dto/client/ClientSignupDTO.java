package com.rm.ifood_backend.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSignupDTO {

  @NotNull(message = "Nome não deve ser nulo")
  private String name;

  @NotNull(message = "Email não deve ser nulo")
  @Email(message = "Deve ser um email válido")
  private String email;

  @NotNull(message = "senha não deve ser nulo")
  @Pattern(regexp = "^(?=.*[^a-zA-Z0-9]).{8,}$", message = "Senha deve conter no mínimo 8 caracteres e 1 caractere especial")
  private String password;

  @NotNull(message = "CPF não deve ser nulo")
  @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter 11 números")
  private String cpf;

  @Pattern(regexp = "^[0-9]{11}$", message = "Número de telefone deve conter 11 digitos, incluindo o DDD")
  private String phone;

  @NotNull(message = "Endereço não deve ser nulo")
  private String address;
}
