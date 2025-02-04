package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.dto.order.CreateOrderDTO;
import com.rm.ifood_backend.dto.order.OrderResponseDTO;
import com.rm.ifood_backend.dto.order.UpdateOrderDTO;
import com.rm.ifood_backend.model.Order;
import com.rm.ifood_backend.service.BaseService;
import com.rm.ifood_backend.service.OrderService;
import com.rm.ifood_backend.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController<Order, CreateOrderDTO, UpdateOrderDTO, OrderResponseDTO> {

  @Autowired
  private OrderService orderService;

  private final OrderMapper orderMapper = OrderMapper.INSTANCE;

  @Override
  protected OrderResponseDTO toResponseDto(Order order) {
    return orderMapper.toResponseDto(order);
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
  protected BaseService<Order> baseService() {
    return orderService;
  }
}