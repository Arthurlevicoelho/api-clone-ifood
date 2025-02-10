package com.rm.ifood_backend.dto.order;

import com.rm.ifood_backend.dto.product.ProductDTO;
import com.rm.ifood_backend.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
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
public class CreateOrderDTO {

  @NotNull(message = "id do cliente não deve ser nulo")
  private UUID client_id;

  @NotNull(message = "id do restaurante não deve ser nulo")
  private UUID restaurant_id;

  private List<ProductDTO> products;

  private OrderStatus status;
}
