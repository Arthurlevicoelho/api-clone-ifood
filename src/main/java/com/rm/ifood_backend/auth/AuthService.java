package com.rm.ifood_backend.auth;

import com.rm.ifood_backend.dto.LoginDTO;
import com.rm.ifood_backend.dto.client.ClientSignupDTO;
import com.rm.ifood_backend.dto.restaurant.RestaurantSignupDTO;
import com.rm.ifood_backend.model.Client;
import com.rm.ifood_backend.model.Restaurant;

public interface AuthService {
  Client registerClient(ClientSignupDTO request);
  Restaurant registerRestaurant(RestaurantSignupDTO request);
  String login(LoginDTO request);
}
