package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.service.BaseService;
import com.rm.ifood_backend.util.ResponseBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping
  public ResponseEntity<Map<String, Object>> getAll() {
    List<ResponseDTO> responseBody = baseService().getAll()
        .stream()
        .map(this::toResponseDto)
        .collect(Collectors.toList());

    return ResponseBuilder.builder(HttpStatus.OK, "Lista de registros obtida com sucesso", responseBody);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> findById(@PathVariable UUID id) {
    T entity = baseService().findById(id);
    ResponseDTO responseBody = toResponseDto(entity);

    if (entity == null) {
      throw new EntityNotFoundException("Entidade não encontrada");
    }
    return ResponseBuilder.builder(HttpStatus.OK, "Registro encontrado", responseBody);
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateDTO createDTO) {
    T entity = toEntityFromCreateDto(createDTO);
    ResponseDTO responseBody = toResponseDto(baseService().create(entity));
    return ResponseBuilder
        .builder(HttpStatus.CREATED, "Registro criado com sucesso", responseBody);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody UpdateDTO updateDTO) {
    T entity = toEntityFromUpdateDto(updateDTO);
    T updatedEntity = baseService().update(id, entity);
    ResponseDTO responseBody = toResponseDto(updatedEntity);

    if (updatedEntity == null) {
      throw new EntityNotFoundException("Entidade não encontrada");
    }

    return ResponseBuilder.builder(HttpStatus.OK, "Entidade atualizada", responseBody);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
    baseService().delete(id);
    return ResponseBuilder.builder(HttpStatus.NO_CONTENT, "Entidade excluída", null);
  }
}
