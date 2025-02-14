package com.rm.ifood_backend.model.order;

import com.rm.ifood_backend.model.product.ProductDTO;
import com.rm.ifood_backend.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public record OrderDTO (
    UUID id,
    UUID client_id,
    UUID restaurant_id,
    List<ProductDTO> products,
    double total_price,
    OrderStatus status
){}
