package com.rm.ifood_backend.service;

import com.rm.ifood_backend.model.Client;
import com.rm.ifood_backend.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends BaseService<Client>{

  @Autowired
  private ClientRepository clientRepository;

  @Override
  protected ClientRepository baseRepository(){
    return clientRepository;
  }

}
