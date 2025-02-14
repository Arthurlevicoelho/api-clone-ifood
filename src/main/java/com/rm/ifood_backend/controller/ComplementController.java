package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.model.complement.ComplementResponseDTO;
import com.rm.ifood_backend.model.complement.CreateComplementDTO;
import com.rm.ifood_backend.model.complement.UpdateComplementDTO;
import com.rm.ifood_backend.service.ComplementService;
import com.rm.ifood_backend.service.ProductService;
import com.rm.ifood_backend.util.ResponseBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/products/{productId}/complements")
public class ComplementController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ComplementService complementService;

  @GetMapping
  public ResponseEntity<Map<String, Object>> getAll(@PathVariable UUID productId) {
    List<ComplementResponseDTO> responseBody = complementService.getAll()
        .stream()
        .filter(comp -> comp.product_id().equals(productId))
        .collect(Collectors.toList());
    return ResponseBuilder.builder(HttpStatus.OK, "Lista de complementos obtida com sucesso", responseBody);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> findById(@PathVariable UUID productId,
                                                      @PathVariable UUID id) {
    ComplementResponseDTO response = complementService.findById(id);
    if (!response.product_id().equals(productId)) {
      throw new EntityNotFoundException("Complemento não pertence ao produto informado.");
    }
    return ResponseBuilder.builder(HttpStatus.OK, "Complemento encontrado", response);
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> create(@PathVariable UUID productId,@Valid @RequestBody CreateComplementDTO createDTO) {
    productService.findById(productId);
    ComplementResponseDTO response = complementService.create(createDTO);
    return ResponseBuilder.builder(HttpStatus.CREATED, "Complemento criado com sucesso", response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> update(@PathVariable UUID productId,
                                                    @PathVariable UUID id,
                                                    @Valid @RequestBody UpdateComplementDTO updateDTO) {
    productService.findById(productId);
    ComplementResponseDTO existing = complementService.findById(id);
    if (!existing.product_id().equals(productId)) {
      throw new EntityNotFoundException("Complemento não pertence ao produto informado.");
    }
    ComplementResponseDTO response = complementService.update(id, updateDTO);
    return ResponseBuilder.builder(HttpStatus.OK, "Complemento atualizado", response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID productId,
                                                    @PathVariable UUID id) {
    ComplementResponseDTO existing = complementService.findById(id);
    if (!existing.product_id().equals(productId)) {
      throw new EntityNotFoundException("Complemento não pertence ao produto informado.");
    }
    complementService.delete(id);
    return ResponseBuilder.builder(HttpStatus.NO_CONTENT, "Complemento excluído", null);
  }
}
