package com.rm.ifood_backend.model.complement;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateComplementDTO {

  @NotNull(message = "Id não deve ser nulo")
  private UUID id;

  @NotNull(message = "Nome não deve ser nulo")
  private String name;

  @NotNull(message = "Preço não deve ser nulo")
  private double price;
}
