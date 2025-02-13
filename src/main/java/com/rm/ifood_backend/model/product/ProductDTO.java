package com.rm.ifood_backend.model.product;

import com.rm.ifood_backend.model.complement.ComplementDTO;
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
public class ProductDTO {
  private UUID id;
  private UUID restaurant_id;
  private String name;
  private String description;
  private double price;
  private boolean available;
  private List<ComplementDTO> complements;
}
