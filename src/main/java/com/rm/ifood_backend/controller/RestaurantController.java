package com.rm.ifood_backend.controller;

import com.rm.ifood_backend.model.restaurant.CreateRestaurantDTO;
import com.rm.ifood_backend.model.restaurant.RestaurantResponseDTO;
import com.rm.ifood_backend.model.restaurant.UpdateRestaurantDTO;
import com.rm.ifood_backend.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController extends BaseController<CreateRestaurantDTO, UpdateRestaurantDTO, RestaurantResponseDTO> {

  @Autowired
  private RestaurantService restaurantService;

  @Override
  protected RestaurantService baseService(){
    return restaurantService;
  }
}