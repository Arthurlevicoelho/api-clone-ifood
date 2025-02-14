package com.rm.ifood_backend.service;

import com.rm.ifood_backend.model.client.Client;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class BaseService<T, CreateDTO, UpdateDTO, ResponseDTO> {

  protected abstract JpaRepository<T, UUID> baseRepository();
  protected abstract ResponseDTO toResponseDto(T entity);
  protected abstract T toEntityFromCreateDto(CreateDTO dto);
  protected abstract T toEntityFromUpdateDto(UpdateDTO dto);

  /**
   * Lista todas as entidades
   *
   * @return Lista das entidades
   */
  public List<ResponseDTO> getAll(){
    return baseRepository().findAll().stream()
        .map(this::toResponseDto)
        .collect(Collectors.toList());
  }
  /**
   * Busca uma entidade pelo ID.
   *
   * @param id UUID da entidade
   * @return A entidade encontrada caso contrário null
   */
  public ResponseDTO findById(UUID id){
    if (baseRepository().existsById(id)){
      T entity = baseRepository().getReferenceById(id);
      return toResponseDto(entity);
    } else {
      throw new EntityNotFoundException("Entidade não encontrada");
    }
  }

  /**
   * Cria uma entidade
   *
   * @param  createDTO Objeto da entidade a ser salva
   * @return A entidade é salva
   */
  public ResponseDTO create(CreateDTO createDTO){
    T entity = toEntityFromCreateDto(createDTO);
    T savedEntity = baseRepository().save(entity);
    return toResponseDto(savedEntity);
  }

  /**
   * Atualiza uma entidade.
   *
   * @param id UUID da entidade a ser atualizada
   * @param updateDTO Dados atualizados da entidade
   *
   * @return A entidade atualizada
   */
  public ResponseDTO update(UUID id, UpdateDTO updateDTO){
    if (baseRepository().existsById(id)){
      T entity = toEntityFromUpdateDto(updateDTO);
      T updatedEntity = baseRepository().save(entity);
      return toResponseDto(updatedEntity);
    } else{
      throw new EntityNotFoundException("Entidade não encontrada.");
    }
  }

  /**
   * Exclui uma entidade pelo Id
   *
   * @param id UUID da entidade a ser excluida.
   */
  public void delete(UUID id){
    if (baseRepository().existsById(id)){
      baseRepository().deleteById(id);
    } else {
      throw new EntityNotFoundException("Entidade não encontrada.");
    }
  }
}
