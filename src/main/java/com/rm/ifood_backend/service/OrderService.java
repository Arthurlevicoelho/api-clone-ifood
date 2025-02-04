package com.rm.ifood_backend.service;

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
    // Calculate the total price of the order
    double totalPrice = calculateTotalPrice(order);

    // Round the total price to 2 decimal places
    BigDecimal roundedTotalPrice = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);

    // Set the rounded total price on the order
    order.setTotal_price(roundedTotalPrice.doubleValue());

    // Save the order and return it
    return orderRepository.save(order);
  }

  private double calculateTotalPrice(Order order) {
    // Assuming each item in the order has a price and we sum them up
    return order.getProducts().stream()
        .mapToDouble(Product::getPrice)
        .sum();
  }
}
