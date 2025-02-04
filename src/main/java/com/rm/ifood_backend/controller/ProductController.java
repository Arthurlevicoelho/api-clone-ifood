package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.dto.product.CreateProductDTO;
import com.rm.ifood_backend.dto.product.ProductResponseDTO;
import com.rm.ifood_backend.dto.product.UpdateProductDTO;
import com.rm.ifood_backend.mapper.ProductMapper;
import com.rm.ifood_backend.model.Product;
import com.rm.ifood_backend.service.BaseService;
import com.rm.ifood_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController<Product, CreateProductDTO, UpdateProductDTO, ProductResponseDTO> {

  @Autowired
  private ProductService productService;

  private final ProductMapper productMapper = ProductMapper.INSTANCE;

  @Override
  protected ProductResponseDTO toResponseDto(Product product) {
    return productMapper.toResponseDto(product);
  }

  @Override
  protected Product toEntityFromCreateDto(CreateProductDTO createProductDTO) {
    return productMapper.toEntityFromCreateDto(createProductDTO);
  }

  @Override
  protected Product toEntityFromUpdateDto(UpdateProductDTO updateProductDTO) {
    return productMapper.toEntityFromUpdateDto(updateProductDTO);
  }

  @Override
  protected BaseService<Product> baseService() {
    return productService;
  }
}