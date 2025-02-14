package com.rm.ifood_backend.model.restaurant;

import com.rm.ifood_backend.model.product.ProductDTO;
import com.rm.ifood_backend.model.order.OrderDTO;
import java.util.List;
import java.util.UUID;

public record RestaurantResponseDTO (
    UUID id,
    String name,
    String email,
    String password,
    String cnpj,
    String address,
    String category,
    String phone,
    List<ProductDTO> menu,
    List<OrderDTO> orders
){}
