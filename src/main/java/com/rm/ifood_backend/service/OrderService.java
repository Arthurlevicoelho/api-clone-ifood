package com.rm.ifood_backend.service;

import com.rm.ifood_backend.enums.OrderStatus;
import com.rm.ifood_backend.mapper.OrderMapper;
import com.rm.ifood_backend.model.client.Client;
import com.rm.ifood_backend.model.complement.ComplementDTO;
import com.rm.ifood_backend.model.order.CreateOrderDTO;
import com.rm.ifood_backend.model.order.OrderResponseDTO;
import com.rm.ifood_backend.model.order.UpdateOrderDTO;
import com.rm.ifood_backend.model.order.Order;
import com.rm.ifood_backend.model.product.ProductDTO;
import com.rm.ifood_backend.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class OrderService extends BaseService<Order, CreateOrderDTO, UpdateOrderDTO, OrderResponseDTO> {

  @Autowired
  OrderRepository orderRepository;

  @Override
  protected OrderRepository baseRepository(){
    return orderRepository;
  }

  private final OrderMapper orderMapper = OrderMapper.INSTANCE;

  @Override
  protected OrderResponseDTO toResponseDto(Order entity) {
    return orderMapper.toResponseDto(entity);
  }

  @Override
  protected Order toEntityFromCreateDto(CreateOrderDTO createDTO) {
    return orderMapper.toEntityFromCreateDto(createDTO);
  }

  @Override
  protected Order toEntityFromUpdateDto(UpdateOrderDTO updateDTO) {
    return orderMapper.toEntityFromUpdateDto(updateDTO);
  }

  @Override
  public OrderResponseDTO create(CreateOrderDTO createDTO){

    double totalPrice = calculateTotalPrice(createDTO);
    BigDecimal roundedTotalPrice = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);

    Order order = toEntityFromCreateDto(createDTO);

    order.setTotal_price(roundedTotalPrice.doubleValue());
    order.setStatus(OrderStatus.PENDENTE);

    Order createdOrder = baseRepository().save(order);

    return toResponseDto(createdOrder);
  }

  private double calculateTotalPrice(CreateOrderDTO order) {

    return order.products().stream()
        .mapToDouble(this::calculateProductPrice)
        .sum();
  }

  private double calculateProductPrice(ProductDTO product){
    double basePrice = product.price();
    double complementsPrice =  product.complements().stream().mapToDouble(ComplementDTO::price).sum();
    return basePrice + complementsPrice;
  }
}