package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.model.client.ClientResponseDTO;
import com.rm.ifood_backend.model.client.CreateClientDTO;
import com.rm.ifood_backend.model.client.UpdateClientDTO;
import com.rm.ifood_backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/clients")
public class ClientController extends BaseController<CreateClientDTO, UpdateClientDTO, ClientResponseDTO> {

  @Autowired
  private ClientService clientService;

  @Override
  protected ClientService baseService(){
    return clientService;
  }
}