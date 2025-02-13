package com.rm.ifood_backend.model.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO {

  private UUID id;

  @NotNull(message = "Id do restaurante não deve ser nulo")
  private UUID restaurant_id;

  @NotNull(message = "Nome não deve ser nulo")
  private String name;

  @NotNull(message = "Descrição não deve ser nulo")
  private String description;

  @NotNull(message = "Preço não deve ser nulo")
  private double price;

  @NotNull(message = "Disponiblidade não deve ser nulo")
  private boolean available;
}