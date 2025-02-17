//package com.rm.ifood_backend;
//
//import com.rm.ifood_backend.model.product.Product;
//import com.rm.ifood_backend.model.restaurant.Restaurant;
//import com.rm.ifood_backend.repository.ProductRepository;
//import com.rm.ifood_backend.service.ProductService;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceTest {
//
//  @InjectMocks
//  private ProductService productService;
//
//  @Mock
//  private ProductRepository productRepository;
//
//  private UUID productId;
//
//  private Product product;
//
//  private Product productUpdater;
//
//  @BeforeEach
//  void setUp(){
//    UUID restaurantId = UUID.randomUUID();
//    Restaurant restaurant = Restaurant.builder()
//        .id(restaurantId)
//        .name("Test Restaurant")
//        .email("restaurant@gmail.com")
//        .password("restaurant123@")
//        .cnpj("123456789082")
//        .category("Sobremesas")
//        .address("rua wzy tal")
//        .phone("85899654765")
//        .build();
//
//    productId = UUID.randomUUID();
//    product = Product.builder()
//        .id(productId)
//        .restaurant(restaurant)
//        .name("Produto de Teste")
//        .description("Um produto para testes")
//        .price(12.50)
//        .available(true)
//        .build();
//
//    productUpdater = Product.builder()
//        .id(productId)
//        .restaurant(restaurant)
//        .name("Produto de Teste Teste")
//        .description("Um produto para testes testes")
//        .price(12.50)
//        .available(true)
//        .build();
//  }
//
//  @Test
//  void shouldReturnAllProdcuts(){
//    when(productRepository.findAll()).thenReturn(List.of(product));
//
//    List<Product> products = productService.getAll();
//
//    assertFalse(products.isEmpty());
//    assertEquals(1, products.size());
//
//    verify(productRepository, times(1)).findAll();
//  }
//
//  @Test
//  void shouldFindProductById(){
//    when(productRepository.findById(productId)).thenReturn(Optional.of(product));
//
//    Product foundProduct = productRepository.findById(productId).orElse(null);
//
//    assertNotNull(foundProduct);
//    assertEquals(productId, foundProduct.getId());
//
//    verify(productRepository, times(1)).findById(productId);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenProductNotFound(){
//    when(productRepository.existsById(productId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> productService.findById(productId));
//
//    verify(productRepository, times(1)).existsById(productId);
//  }
//
//  @Test
//  void shouldCreateProduct(){
//    when(productService.create(product)).thenReturn(product);
//
//    Product savedProduct = productService.create(product);
//
//    assertNotNull(savedProduct);
//    assertEquals(productId, savedProduct.getId());
//
//    verify(productRepository, times(1)).save(product);
//  }
//
//  @Test
//  void shouldUpdateProduct(){
//    when(productRepository.existsById(productId)).thenReturn(true);
//    when(productRepository.save(productUpdater)).thenReturn(productUpdater);
//
//    Product updatedProduct = productService.update(productId, productUpdater);
//
//    assertNotNull(updatedProduct);
//    assertEquals(productId, updatedProduct.getId());
//
//    verify(productRepository, times(1)).existsById(productId);
//    verify(productRepository, times(1)).save(productUpdater);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenUpdatingNonExistentProduct(){
//    when(productRepository.existsById(productId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> productService.update(productId, productUpdater));
//
//    verify(productRepository, times(1)).existsById(productId);
//    verify(productRepository, never()).findById(any(UUID.class));
//    verify(productRepository, never()).save(any(Product.class));
//  }
//
//  @Test
//  void shouldDeleteProduct(){
//    when(productRepository.existsById(productId)).thenReturn(true);
//
//    doNothing().when(productRepository).deleteById(productId);
//
//    assertDoesNotThrow(() -> productService.delete(productId));
//
//    verify(productRepository, times(1)).existsById(productId);
//    verify(productRepository, times(1)).deleteById(productId);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenDeletingNonExistentProduct(){
//    when(productRepository.existsById(productId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> productService.delete(productId));
//
//    verify(productRepository, times(1)).existsById(productId);
//    verify(productRepository, never()).deleteById(any(UUID.class));
//  }
//}
