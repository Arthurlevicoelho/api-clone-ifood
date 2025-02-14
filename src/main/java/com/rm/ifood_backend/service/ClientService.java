package com.rm.ifood_backend.service;

import com.rm.ifood_backend.mapper.ClientMapper;
import com.rm.ifood_backend.model.client.Client;
import com.rm.ifood_backend.model.client.ClientResponseDTO;
import com.rm.ifood_backend.model.client.CreateClientDTO;
import com.rm.ifood_backend.model.client.UpdateClientDTO;
import com.rm.ifood_backend.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends BaseService<Client, CreateClientDTO, UpdateClientDTO, ClientResponseDTO>{

  @Autowired
  private ClientRepository clientRepository;

  @Override
  protected ClientRepository baseRepository(){
    return clientRepository;
  }

  private final ClientMapper clientMapper = ClientMapper.INSTANCE;

  @Override
  protected ClientResponseDTO toResponseDto(Client client) {
    return clientMapper.toResponseDTO(client);
  }

  @Override
  protected Client toEntityFromCreateDto(CreateClientDTO createDTO) {
    return clientMapper.toEntityFromCreateDto(createDTO);
  }

  @Override
  protected Client toEntityFromUpdateDto(UpdateClientDTO updateDTO) {
    return clientMapper.toEntityFromUpdateDto(updateDTO);
  }
}
