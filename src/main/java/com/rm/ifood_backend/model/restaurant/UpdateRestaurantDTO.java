package com.rm.ifood_backend.model.restaurant;

import com.rm.ifood_backend.model.product.ProductDTO;

import java.util.List;
import java.util.UUID;

public record UpdateRestaurantDTO (
    UUID id,

    String name,

    String email,

    String password,

    String cnpj,

    String phone,

    String address,

    String category,

    List<ProductDTO> menu
){}
