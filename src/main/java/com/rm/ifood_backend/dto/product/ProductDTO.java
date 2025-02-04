package com.rm.ifood_backend.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
  private UUID id;
  private UUID restaurant_id;
  private String name;
  private String description;
  private double price;
  private boolean available;
}
