package com.rm.ifood_backend.model.complement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplementDTO {

  private UUID id;
  private String name;
  private double price;
}
