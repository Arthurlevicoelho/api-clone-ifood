package com.rm.ifood_backend.model.restaurant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantDTO {

  @NotNull(message = "Nome não deve ser nulo")
  private String name;

  @NotNull(message = "Email não deve ser nulo")
  @Email(message = "Deve ser um email válido")
  private String email;

  @NotNull(message = "Senha não deve ser nulo")
  @Pattern(regexp = "^(?=.*[^a-zA-Z0-9]).{8,}$", message = "Senha deve conter no mínimo 8 caracteres e 1 caractere especial")
  private String password;

  @NotNull(message = "CNPJ não deve ser nulo")
  @Pattern(regexp = "^[0-9]{14}$", message = "CNPJ deve conter 14 números")
  private String cnpj;

  @Pattern(regexp = "^[0-9]{11}$", message = "Número de telefone deve conter 11 digitos, incluindo DDD")
  private String phone;

  @NotNull(message = "Endereço não deve ser nulo")
  private String address;

  @NotNull(message = "Categoria não deve ser nulo")
  private String category;
}
