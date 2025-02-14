package com.rm.ifood_backend.model.complement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
public record ComplementDTO (

    UUID id,
    String name,
    double price
){ }
