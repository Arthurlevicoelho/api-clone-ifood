package com.rm.ifood_backend.model.complement;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateComplementDTO {

  @NotNull(message = "Nome não deve ser nulo")
  private String name;

  @NotNull(message = "Preço não deve ser nulo")
  private double price;
}
