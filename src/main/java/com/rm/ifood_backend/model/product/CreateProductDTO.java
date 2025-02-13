package com.rm.ifood_backend.model.product;

import com.rm.ifood_backend.model.complement.ComplementDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {

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

  private List<ComplementDTO> complements = new ArrayList<>();
}