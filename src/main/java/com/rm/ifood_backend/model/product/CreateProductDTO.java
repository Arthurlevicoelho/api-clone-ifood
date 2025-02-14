package com.rm.ifood_backend.model.product;

import com.rm.ifood_backend.model.complement.ComplementDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateProductDTO (

    @NotNull(message = "Id do restaurante não deve ser nulo")
    UUID restaurant_id,

    @NotNull(message = "Nome não deve ser nulo")
    String name,

    @NotNull(message = "Descrição não deve ser nulo")
    String description,

    @NotNull(message = "Preço não deve ser nulo")
    double price,

    @NotNull(message = "Disponiblidade não deve ser nulo")
    boolean available,

    List<ComplementDTO> complements
){}