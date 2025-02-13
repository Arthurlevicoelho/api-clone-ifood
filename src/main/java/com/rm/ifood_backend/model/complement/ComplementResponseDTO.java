package com.rm.ifood_backend.model.complement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplementResponseDTO {

  private UUID id;

  private UUID product_id;

  private String name;

  private double price;

}
