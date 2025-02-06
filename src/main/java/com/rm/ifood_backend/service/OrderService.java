package com.rm.ifood_backend.service;

import com.rm.ifood_backend.enums.OrderStatus;
import com.rm.ifood_backend.model.Product;
import com.rm.ifood_backend.model.Order;
import com.rm.ifood_backend.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class OrderService extends BaseService<Order> {

  @Autowired
  OrderRepository orderRepository;

  @Override
  protected OrderRepository baseRepository(){
    return orderRepository;
  }

  @Override
  public Order create(Order order){

    double totalPrice = calculateTotalPrice(order);

    BigDecimal roundedTotalPrice = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);

    order.setTotal_price(roundedTotalPrice.doubleValue());
    order.setStatus(OrderStatus.PENDENTE);

    return orderRepository.save(order);
  }

  private double calculateTotalPrice(Order order) {
    return order.getProducts().stream()
        .mapToDouble(Product::getPrice)
        .sum();
  }
}
