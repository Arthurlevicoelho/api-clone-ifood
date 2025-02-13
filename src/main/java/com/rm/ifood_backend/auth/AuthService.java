package com.rm.ifood_backend.auth;

import com.rm.ifood_backend.model.LoginDTO;
import com.rm.ifood_backend.model.client.ClientSignupDTO;
import com.rm.ifood_backend.model.restaurant.RestaurantSignupDTO;
import com.rm.ifood_backend.model.client.Client;
import com.rm.ifood_backend.model.restaurant.Restaurant;

public interface AuthService {
  Client registerClient(ClientSignupDTO request);
  Restaurant registerRestaurant(RestaurantSignupDTO request);
  String login(LoginDTO request);
}
