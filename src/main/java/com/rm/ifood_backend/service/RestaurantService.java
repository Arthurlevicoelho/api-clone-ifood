package com.rm.ifood_backend.service;

import com.rm.ifood_backend.model.Restaurant;
import com.rm.ifood_backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService extends BaseService<Restaurant> {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Override
  protected RestaurantRepository baseRepository() {
    return restaurantRepository;
  }

}
