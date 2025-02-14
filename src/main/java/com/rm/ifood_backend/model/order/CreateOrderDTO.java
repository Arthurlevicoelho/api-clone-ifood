package com.rm.ifood_backend.model.order;

import com.rm.ifood_backend.model.product.ProductDTO;
import com.rm.ifood_backend.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateOrderDTO (

    @NotNull(message = "id do cliente não deve ser nulo")
    UUID client_id,

    @NotNull(message = "id do restaurante não deve ser nulo")
    UUID restaurant_id,

    List<ProductDTO> products,

    double total_price,

    OrderStatus status
){}
