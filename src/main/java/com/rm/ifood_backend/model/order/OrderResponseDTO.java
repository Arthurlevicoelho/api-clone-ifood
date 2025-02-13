package com.rm.ifood_backend.model.order;

import com.rm.ifood_backend.model.product.ProductDTO;
import com.rm.ifood_backend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
  private UUID id;
  private UUID client_id;
  private UUID restaurant_id;
  private List<ProductDTO> products;
  private double total_price;
  private OrderStatus status;
}
