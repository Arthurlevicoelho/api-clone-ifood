package com.rm.ifood_backend.model;

import com.rm.ifood_backend.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {

  @NotNull(message = "Email não deve ser nulo")
  @Email(message = "Deve ser um email válido")
  private String email;

  @NotNull(message = "Senha não deve ser nulo")
  private String password;

  @NotNull(message = "Tipo de Usuário não deve ser nulo")
  private UserType userType;
}
