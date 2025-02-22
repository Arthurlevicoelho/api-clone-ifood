package com.rm.ifood_backend.repository;

import com.rm.ifood_backend.model.client.Client;

import java.util.Optional;

public interface ClientRepository extends BaseRepository<Client>{
  Optional<Client> findByEmail(String email);
}
