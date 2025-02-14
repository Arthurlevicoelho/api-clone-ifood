package com.rm.ifood_backend.model.product;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateProductDTO (

    UUID id,

    @NotNull(message = "Id do restaurante não deve ser nulo")
    UUID restaurant_id,

    @NotNull(message = "Nome não deve ser nulo")
    String name,

    @NotNull(message = "Descrição não deve ser nulo")
    String description,

    @NotNull(message = "Preço não deve ser nulo")
    double price,

    @NotNull(message = "Disponiblidade não deve ser nulo")
    boolean available
){}