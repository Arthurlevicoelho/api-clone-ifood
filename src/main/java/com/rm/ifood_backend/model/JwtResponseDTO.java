package com.rm.ifood_backend.model;


import java.util.UUID;

public record JwtResponseDTO (
    UUID id,
    String token
){}
