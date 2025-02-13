package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.mapper.ComplementMapper;
import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.model.complement.ComplementResponseDTO;
import com.rm.ifood_backend.model.complement.CreateComplementDTO;
import com.rm.ifood_backend.model.complement.UpdateComplementDTO;
import com.rm.ifood_backend.model.product.Product;
import com.rm.ifood_backend.service.BaseService;
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
public class ComplementController{

  @Autowired
  private ProductService productService;

  @Autowired
  private ComplementService complementService;

  private final ComplementMapper complementMapper = ComplementMapper.INSTANCE;

  protected ComplementResponseDTO toResponseDto(Complement complement) {
    return complementMapper.toResponseDto(complement);
  }

  protected Complement toEntityFromCreateDto(CreateComplementDTO createComplementDTO){
    return complementMapper.toEntityFromCreateDto(createComplementDTO);
  }

  protected Complement toEntityFromUpdateDto(UpdateComplementDTO updateComplementDTO){
    return complementMapper.toEntityFromUpdateDto(updateComplementDTO);
  }

  protected BaseService<Complement> baseService() { return complementService; }

  @GetMapping
  public ResponseEntity<Map<String, Object>> getAll(@PathVariable UUID productId) {
    List<ComplementResponseDTO> responseBody = complementService.getAll()
        .stream()
        .filter(comp -> comp.getProduct().getId().equals(productId))
        .map(this::toResponseDto)
        .collect(Collectors.toList());
    return ResponseBuilder.builder(HttpStatus.OK, "Lista de complementos obtida com sucesso", responseBody);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> findById(@PathVariable UUID productId,
                                                      @PathVariable UUID id) {
    Complement complement = complementService.findById(id);
    if (!complement.getProduct().getId().equals(productId)) {
      throw new EntityNotFoundException("Complemento não pertence ao produto informado.");
    }
    ComplementResponseDTO response = toResponseDto(complement);
    return ResponseBuilder.builder(HttpStatus.OK, "Complemento encontrado", response);
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> create(@PathVariable UUID productId,
                                                    @Valid @RequestBody CreateComplementDTO createDTO) {
    Product product = productService.findById(productId);

    Complement complement = complementMapper.toEntityFromCreateDto(createDTO);

    complement.setProduct(product);

    Complement savedComplement = baseService().create(complement);

    ComplementResponseDTO response = toResponseDto(savedComplement);

    return ResponseBuilder.builder(HttpStatus.CREATED, "Complemento criado com sucesso", response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> update(@PathVariable UUID productId,
                                                    @PathVariable UUID id,
                                                    @Valid @RequestBody UpdateComplementDTO updateDTO) {
    productService.findById(productId);
    Complement existingComplement = complementService.findById(id);
    if (!existingComplement.getProduct().getId().equals(productId)) {
      throw new EntityNotFoundException("Complemento não pertence ao produto informado.");
    }
    Complement complement = complementMapper.toEntityFromUpdateDto(updateDTO);
    complement.setId(id);

    complement.setProduct(existingComplement.getProduct());
    Complement updatedComplement = baseService().update(id, complement);
    ComplementResponseDTO response = toResponseDto(updatedComplement);
    return ResponseBuilder.builder(HttpStatus.OK, "Complemento atualizado", response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID productId,
                                                    @PathVariable UUID id) {
    Complement complement = complementService.findById(id);
    if (!complement.getProduct().getId().equals(productId)) {
      throw new EntityNotFoundException("Complemento não pertence ao produto informado.");
    }
    baseService().delete(id);
    return ResponseBuilder.builder(HttpStatus.NO_CONTENT, "Complemento excluído", null);
  }
}
