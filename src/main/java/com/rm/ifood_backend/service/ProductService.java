package com.rm.ifood_backend.service;

import com.rm.ifood_backend.mapper.ProductMapper;
import com.rm.ifood_backend.model.product.CreateProductDTO;
import com.rm.ifood_backend.model.product.Product;
import com.rm.ifood_backend.model.product.ProductResponseDTO;
import com.rm.ifood_backend.model.product.UpdateProductDTO;
import com.rm.ifood_backend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, CreateProductDTO, UpdateProductDTO, ProductResponseDTO> {

  @Autowired
  ProductRepository productRepository;

  @Override
  protected ProductRepository baseRepository(){
    return productRepository;
  }

  private final ProductMapper productMapper = ProductMapper.INSTANCE;

  @Override
  protected ProductResponseDTO toResponseDto(Product entity) {
    return productMapper.toResponseDto(entity);
  }

  @Override
  protected Product toEntityFromCreateDto(CreateProductDTO createDTO) {
    return productMapper.toEntityFromCreateDto(createDTO);
  }

  @Override
  protected Product toEntityFromUpdateDto(UpdateProductDTO updateDTO) {
    return productMapper.toEntityFromUpdateDto(updateDTO);
  }
}
