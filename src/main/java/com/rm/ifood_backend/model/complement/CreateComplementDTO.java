package com.rm.ifood_backend.model.complement;

import jakarta.validation.constraints.NotNull;

public record CreateComplementDTO (

    @NotNull(message = "Nome não deve ser nulo")
    String name,

    @NotNull(message = "Preço não deve ser nulo")
    double price
){}
