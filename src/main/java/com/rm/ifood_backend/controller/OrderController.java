package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.model.order.CreateOrderDTO;
import com.rm.ifood_backend.model.order.OrderResponseDTO;
import com.rm.ifood_backend.model.order.UpdateOrderDTO;
import com.rm.ifood_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController<CreateOrderDTO, UpdateOrderDTO, OrderResponseDTO> {

  @Autowired
  private OrderService orderService;

  @Override
  protected OrderService baseService() {
    return orderService;
  }
}