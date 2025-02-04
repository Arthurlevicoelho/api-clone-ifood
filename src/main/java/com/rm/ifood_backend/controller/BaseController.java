package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
public abstract class BaseController<T, CreateDTO, UpdateDTO, ResponseDTO> {

  protected abstract BaseService<T> baseService();

  protected abstract ResponseDTO toResponseDto(T entity);

  protected abstract T toEntityFromCreateDto(CreateDTO dto);

  protected abstract T toEntityFromUpdateDto(UpdateDTO dto);

  private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message, Object data) {
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("status", status.value());
    responseMap.put("message", message);
    responseMap.put("body", data);
    return new ResponseEntity<>(responseMap, status);
  }

  @GetMapping
  public List<ResponseDTO> getAll() {
    return baseService().getAll()
        .stream()
        .map(this::toResponseDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> findById(@PathVariable UUID id) {
    T entity = baseService().findById(id);
    if (entity == null) {
      return ResponseEntity.notFound().build();
    }
    return buildResponse(HttpStatus.OK, "Registro encontrado", toResponseDto(entity));
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateDTO createDTO) {
    T entity = toEntityFromCreateDto(createDTO);
    return buildResponse(HttpStatus.CREATED, "Registro criado com sucesso", toResponseDto(baseService().create(entity)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody UpdateDTO updateDTO) {
    T entity = toEntityFromUpdateDto(updateDTO);
    T updatedEntity = baseService().update(id, entity);
    if (updatedEntity == null) {
      throw new EntityNotFoundException("Entidade não encontrada");
    }

    return buildResponse(HttpStatus.OK, "Entidade atualizada", toResponseDto(updatedEntity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
    baseService().delete(id);
    return buildResponse(HttpStatus.NO_CONTENT, "Entidade excluída", null);
  }
}
