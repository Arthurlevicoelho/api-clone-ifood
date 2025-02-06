package com.rm.ifood_backend.auth;

import com.rm.ifood_backend.dto.JwtResponseDTO;
import com.rm.ifood_backend.dto.LoginDTO;
import com.rm.ifood_backend.dto.client.ClientSignupDTO;
import com.rm.ifood_backend.dto.restaurant.RestaurantSignupDTO;
import com.rm.ifood_backend.model.Client;
import com.rm.ifood_backend.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/signup/client")
  public ResponseEntity<Client> signupClient(@RequestBody ClientSignupDTO request){
    Client client = authService.registerClient(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(client);
  }

  @PostMapping("/signup/restaurant")
  public ResponseEntity<Restaurant> signupRestaurant(@RequestBody RestaurantSignupDTO request) {
    Restaurant restaurant = authService.registerRestaurant(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginDTO request) {
    String token = authService.login(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponseDTO(token));
  }
}
