package com.rm.ifood_backend.service;

import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.repository.ComplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplementService extends BaseService<Complement> {

  @Autowired
  private ComplementRepository complementRepository;

  @Override
  protected ComplementRepository baseRepository() {return complementRepository; }
}
