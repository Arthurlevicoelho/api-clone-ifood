package com.rm.ifood_backend.service;

import com.rm.ifood_backend.mapper.RestaurantMapper;
import com.rm.ifood_backend.model.restaurant.CreateRestaurantDTO;
import com.rm.ifood_backend.model.restaurant.Restaurant;
import com.rm.ifood_backend.model.restaurant.RestaurantResponseDTO;
import com.rm.ifood_backend.model.restaurant.UpdateRestaurantDTO;
import com.rm.ifood_backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class RestaurantService extends BaseService<Restaurant, CreateRestaurantDTO, UpdateRestaurantDTO, RestaurantResponseDTO> {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected RestaurantRepository baseRepository() {
    return restaurantRepository;
  }

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
}
