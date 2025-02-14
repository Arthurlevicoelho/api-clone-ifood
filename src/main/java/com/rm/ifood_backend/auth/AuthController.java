package com.rm.ifood_backend.auth;

import com.rm.ifood_backend.model.JwtResponseDTO;
import com.rm.ifood_backend.model.LoginDTO;
import com.rm.ifood_backend.model.client.ClientSignupDTO;
import com.rm.ifood_backend.model.restaurant.RestaurantSignupDTO;
import com.rm.ifood_backend.model.client.Client;
import com.rm.ifood_backend.model.restaurant.Restaurant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/signup/client")
  public ResponseEntity<Client> signupClient(@Valid @RequestBody ClientSignupDTO request){
    Client client = authService.registerClient(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(client);
  }

  @PostMapping("/signup/restaurant")
  public ResponseEntity<Restaurant> signupRestaurant(@Valid @RequestBody RestaurantSignupDTO request) {
    Restaurant restaurant = authService.registerRestaurant(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody LoginDTO request) {
    JwtResponseDTO response = authService.login(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
