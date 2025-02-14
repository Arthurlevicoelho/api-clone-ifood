package com.rm.ifood_backend.model.complement;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateComplementDTO (

    @NotNull(message = "Id não deve ser nulo")
    UUID id,

    @NotNull(message = "Nome não deve ser nulo")
    String name,

    @NotNull(message = "Preço não deve ser nulo")
    double price
){}
