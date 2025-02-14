package com.rm.ifood_backend.model.complement;

import java.util.UUID;

public record ComplementResponseDTO (

    UUID id,

    UUID product_id,

    String name,

    double price
){}
