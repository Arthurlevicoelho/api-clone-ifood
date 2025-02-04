package com.rm.ifood_backend.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public abstract class BaseService<T> {

  protected abstract JpaRepository<T, UUID> baseRepository();

  /**
   * Lista todas as entidades
   *
   * @return Lista das entidades
   */
  public List<T> getAll(){
    return baseRepository().findAll();
  }

  /**
   * Busca uma entidade pelo ID.
   *
   * @param id UUID da entidade
   * @return A entidade encontrada caso contrário null
   */
  public T findById(UUID id){
    if (baseRepository().existsById(id)){
      return baseRepository().getReferenceById(id);
    } else {
      throw new EntityNotFoundException("Entidade não encontrada");
    }
  }

  /**
   * Cria uma entidade
   *
   * @param  entity Objeto da entidade a ser salva
   * @return A entidade é salva
   */
  public T create(T entity){
    return baseRepository().save(entity);
  }

  /**
   * Atualiza uma entidade.
   *
   * @param id UUID da entidade a ser atualizada
   * @param entity Dados atualizados da entidade
   *
   * @return A entidade atualizada
   */
  public T update(UUID id, T entity){
    if (baseRepository().existsById(id)){
      return baseRepository().save(entity);
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
