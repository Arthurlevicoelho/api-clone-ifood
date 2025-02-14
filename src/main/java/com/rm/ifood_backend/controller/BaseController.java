package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.service.BaseService;
import com.rm.ifood_backend.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Validated
public abstract class BaseController<CreateDTO, UpdateDTO, ResponseDTO> {

  protected abstract BaseService<?, CreateDTO, UpdateDTO, ResponseDTO> baseService();

  @GetMapping
  public ResponseEntity<Map<String, Object>> getAll() {
    List<ResponseDTO> responseBody = baseService().getAll();
    return ResponseBuilder.builder(HttpStatus.OK, "Lista de registros obtida com sucesso", responseBody);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> findById(@PathVariable UUID id) {
    ResponseDTO responseBody = baseService().findById(id);
    return ResponseBuilder.builder(HttpStatus.OK, "Registro encontrado", responseBody);
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateDTO createDTO) {
    ResponseDTO responseBody = baseService().create(createDTO);
    return ResponseBuilder
        .builder(HttpStatus.CREATED, "Registro criado com sucesso", responseBody);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody UpdateDTO updateDTO) {
    ResponseDTO responseBody = baseService().update(id, updateDTO);
    return ResponseBuilder.builder(HttpStatus.OK, "Entidade atualizada", responseBody);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
    baseService().delete(id);
    return ResponseBuilder.builder(HttpStatus.NO_CONTENT, "Entidade excluída", null);
  }
}
