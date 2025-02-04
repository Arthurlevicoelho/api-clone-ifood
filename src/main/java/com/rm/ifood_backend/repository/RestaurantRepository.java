package com.rm.ifood_backend.repository;

import com.rm.ifood_backend.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends BaseRepository<Restaurant>{
  Optional<Restaurant> findByEmail(String email);
}
