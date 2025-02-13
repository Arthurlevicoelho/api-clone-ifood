package com.rm.ifood_backend.service;

import com.rm.ifood_backend.enums.OrderStatus;
import com.rm.ifood_backend.model.complement.Complement;
import com.rm.ifood_backend.model.product.Product;
import com.rm.ifood_backend.model.order.Order;
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

    System.out.println(order);

    double totalPrice = calculateTotalPrice(order);

    BigDecimal roundedTotalPrice = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);

    order.setTotal_price(roundedTotalPrice.doubleValue());
    order.setStatus(OrderStatus.PENDENTE);

    return baseRepository().save(order);
  }

  private double calculateTotalPrice(Order order) {

    return order.getProducts().stream()
        .mapToDouble(this::calculateProductPrice)
        .sum();
  }

  private double calculateProductPrice(Product product){
    System.out.println("Produto: " + product);
    double basePrice = product.getPrice();
    double complementsPrice =  product.getComplements().stream().mapToDouble(Complement::getPrice).sum();
    return basePrice + complementsPrice;
  }
}