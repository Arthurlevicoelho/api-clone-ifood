package com.rm.ifood_backend.model.product;

import com.rm.ifood_backend.model.complement.ComplementDTO;

import java.util.List;
import java.util.UUID;

public record ProductDTO (
    UUID id,
    UUID restaurant_id,
    String name,
    String description,
    double price,
    boolean available,
    List<ComplementDTO> complements
){}
