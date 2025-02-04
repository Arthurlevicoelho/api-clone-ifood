package com.rm.ifood_backend.service;

import com.rm.ifood_backend.model.Product;
import com.rm.ifood_backend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product> {

  @Autowired
  ProductRepository productRepository;

  @Override
  protected ProductRepository baseRepository(){
    return productRepository;
  }

}
