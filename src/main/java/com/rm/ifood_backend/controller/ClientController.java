package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.model.client.ClientResponseDTO;
import com.rm.ifood_backend.model.client.CreateClientDTO;
import com.rm.ifood_backend.model.client.UpdateClientDTO;
import com.rm.ifood_backend.model.client.Client;
import com.rm.ifood_backend.service.BaseService;
import com.rm.ifood_backend.service.ClientService;
import com.rm.ifood_backend.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/clients")
public class ClientController extends BaseController<Client, CreateClientDTO, UpdateClientDTO, ClientResponseDTO> {

  @Autowired
  private ClientService clientService;

  private final ClientMapper clientMapper = ClientMapper.INSTANCE;

  @Override
  protected ClientResponseDTO toResponseDto(Client client) {
    return clientMapper.toResponseDTO(client);
  }

  @Override
  protected Client toEntityFromCreateDto(CreateClientDTO createClientDTO) {
    return clientMapper.toEntityFromCreateDto(createClientDTO);
  }

  @Override
  protected Client toEntityFromUpdateDto(UpdateClientDTO updateClientDTO) {
    return clientMapper.toEntityFromUpdateDto(updateClientDTO);
  }

  @Override
  protected BaseService<Client> baseService() {
    return clientService;
  }
}