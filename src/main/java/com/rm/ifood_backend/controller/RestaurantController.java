package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.dto.restaurant.CreateRestaurantDTO;
import com.rm.ifood_backend.dto.restaurant.RestaurantResponseDTO;
import com.rm.ifood_backend.dto.restaurant.UpdateRestaurantDTO;
import com.rm.ifood_backend.mapper.RestaurantMapper;
import com.rm.ifood_backend.model.Restaurant;
import com.rm.ifood_backend.service.BaseService;
import com.rm.ifood_backend.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController extends BaseController<Restaurant, CreateRestaurantDTO, UpdateRestaurantDTO, RestaurantResponseDTO> {

  @Autowired
  private RestaurantService restaurantService;

  private final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;

  @Override
  protected RestaurantResponseDTO toResponseDto(Restaurant restaurant) {
    return restaurantMapper.toResponseDto(restaurant);
  }

  @Override
  protected Restaurant toEntityFromCreateDto(CreateRestaurantDTO createDTO) {
    return restaurantMapper.toEntityFromCreateDto(createDTO);
  }

  @Override
  protected Restaurant toEntityFromUpdateDto(UpdateRestaurantDTO updateDTO) {
    return restaurantMapper.toEntityFromUpdateDto(updateDTO);
  }

  @Override
  protected BaseService<Restaurant> baseService() {
    return restaurantService;
  }

}