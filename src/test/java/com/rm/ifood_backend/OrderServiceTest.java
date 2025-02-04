package com.rm.ifood_backend;

import com.rm.ifood_backend.enums.OrderStatus;
import com.rm.ifood_backend.model.Client;
import com.rm.ifood_backend.model.Product;
import com.rm.ifood_backend.model.Order;
import com.rm.ifood_backend.model.Restaurant;
import com.rm.ifood_backend.repository.OrderRepository;
import com.rm.ifood_backend.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

  @InjectMocks
  private OrderService orderService;

  @Mock
  private OrderRepository orderRepository;

  private UUID orderId;

  private Order order;

  private Order orderUpdater;

  @BeforeEach
  void setUp(){
    UUID restaurantId = UUID.randomUUID();
    Restaurant restaurant = Restaurant.builder()
        .id(restaurantId)
        .name("Test Restaurant")
        .email("restaurant@gmail.com")
        .password("restaurant123@")
        .cnpj("123456789082")
        .category("Sobremesas")
        .address("rua wzy tal")
        .phone("85899654765")
        .build();

    UUID clientId = UUID.randomUUID();
    Client client = Client.builder()
        .id(clientId)
        .name("TestUser")
        .email("teste@gmail.com")
        .password("teste123@")
        .cpf("12345678910")
        .address("rua xyz")
        .phone("99123456789")
        .build();

    UUID productId = UUID.randomUUID();
    Product product = Product.builder()
        .id(productId)
        .restaurant(restaurant)
        .name("Produto de Teste")
        .description("Um produto para testes")
        .price(12.50)
        .available(true)
        .build();

    orderId = UUID.randomUUID();
    order = Order.builder()
        .id(orderId)
        .client(client)
        .restaurant(restaurant)
        .products(List.of(product))
        .status(OrderStatus.valueOf("PENDENTE"))
        .build();

    orderUpdater = Order.builder()
        .id(orderId)
        .client(client)
        .restaurant(restaurant)
        .products(List.of(product))
        .status(OrderStatus.valueOf("PREPARANDO"))
        .build();
  }

  @Test
  void shouldReturnAllOrders(){
    when(orderRepository.findAll()).thenReturn(List.of(order));

    List<Order> orders = orderService.getAll();

    assertFalse(orders.isEmpty());
    assertEquals(1, orders.size());

    verify(orderRepository, times(1)).findAll();
  }

  @Test
  void shouldFindOrderById(){
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    Order foundOrder = orderRepository.findById(orderId).orElse(null);

    assertNotNull(foundOrder);
    assertEquals(orderId, foundOrder.getId());

    verify(orderRepository, times(1)).findById(orderId);
  }

  @Test
  void shouldThrowExceptionWhenOrderNotFound(){
    when(orderRepository.existsById(orderId)).thenReturn(false);

    assertThrows(EntityNotFoundException.class, () -> orderService.findById(orderId));

    verify(orderRepository, times(1)).existsById(orderId);
  }

  @Test
  void shouldCreateOrder(){
    when(orderService.create(order)).thenReturn(order);

    Order savedOrder = orderService.create(order);

    assertNotNull(savedOrder);
    assertEquals(orderId, savedOrder.getId());

    verify(orderRepository, times(1)).save(order);
  }

  @Test
  void shouldUpdateOrder(){
    when(orderRepository.existsById(orderId)).thenReturn(true);
    when(orderRepository.save(orderUpdater)).thenReturn(orderUpdater);

    Order updatedOrder = orderService.update(orderId, orderUpdater);

    assertNotNull(updatedOrder);
    assertEquals(orderId, updatedOrder.getId());

    verify(orderRepository, times(1)).existsById(orderId);
    verify(orderRepository, times(1)).save(orderUpdater);
  }

  @Test
  void shouldThrowExceptionWhenUpdatingNonExistentOrder(){
    when(orderRepository.existsById(orderId)).thenReturn(false);

    assertThrows(EntityNotFoundException.class, () -> orderService.update(orderId, orderUpdater));

    verify(orderRepository, times(1)).existsById(orderId);
    verify(orderRepository, never()).findById(any(UUID.class));
    verify(orderRepository, never()).save(any(Order.class));
  }

  @Test
  void shouldDeleteOrder(){
    when(orderRepository.existsById(orderId)).thenReturn(true);

    doNothing().when(orderRepository).deleteById(orderId);

    assertDoesNotThrow(() -> orderService.delete(orderId));

    verify(orderRepository, times(1)).existsById(orderId);
    verify(orderRepository, times(1)).deleteById(orderId);
  }

  @Test
  void shouldThrowExceptionWhenDeletingNonExistentOrder(){
    when(orderRepository.existsById(orderId)).thenReturn(false);

    assertThrows(EntityNotFoundException.class, () -> orderService.delete(orderId));

    verify(orderRepository, times(1)).existsById(orderId);
    verify(orderRepository, never()).deleteById(any(UUID.class));
  }
}
