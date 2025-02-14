package com.rm.ifood_backend.model;

import com.rm.ifood_backend.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginDTO (
    @NotNull(message = "Email não deve ser nulo")
    @Email(message = "Deve ser um email válido")
    String email,

    @NotNull(message = "Senha não deve ser nulo")
    String password,

    @NotNull(message = "Tipo de Usuário não deve ser nulo")
    UserType userType
){}
