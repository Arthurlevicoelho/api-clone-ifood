package com.rm.ifood_backend.service;

import com.rm.ifood_backend.model.restaurant.Restaurant;
import com.rm.ifood_backend.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class RestaurantService extends BaseService<Restaurant> {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected RestaurantRepository baseRepository() {
    return restaurantRepository;
  }

  @Override
  public Restaurant update(UUID id, Restaurant restaurant){
    if (baseRepository().existsById(id)){
      Restaurant entity = restaurantRepository.getReferenceById(id);

      if (restaurant.getPassword() != null && !restaurant.getPassword().trim().isEmpty()) {
        restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
      } else {
        restaurant.setPassword(entity.getPassword());
      }

      return baseRepository().save(restaurant);
    } else{
      throw new EntityNotFoundException("Entidade não encontrada.");
    }
  }
}
