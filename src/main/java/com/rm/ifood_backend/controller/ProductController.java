package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.model.product.CreateProductDTO;
import com.rm.ifood_backend.model.product.ProductResponseDTO;
import com.rm.ifood_backend.model.product.UpdateProductDTO;
import com.rm.ifood_backend.mapper.ProductMapper;
import com.rm.ifood_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController<CreateProductDTO, UpdateProductDTO, ProductResponseDTO> {

  @Autowired
  private ProductService productService;

  private final ProductMapper productMapper = ProductMapper.INSTANCE;

  @Override
  protected ProductService baseService() {
    return productService;
  }
}