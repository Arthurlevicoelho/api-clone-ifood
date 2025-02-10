package com.rm.ifood_backend.auth;

import com.rm.ifood_backend.dto.LoginDTO;
import com.rm.ifood_backend.dto.client.ClientSignupDTO;
import com.rm.ifood_backend.dto.restaurant.RestaurantSignupDTO;
import com.rm.ifood_backend.enums.UserType;
import com.rm.ifood_backend.model.Client;
import com.rm.ifood_backend.model.Restaurant;
import com.rm.ifood_backend.repository.ClientRepository;
import com.rm.ifood_backend.repository.RestaurantRepository;
import com.rm.ifood_backend.service.ClientService;
import com.rm.ifood_backend.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

  @Autowired
  private ClientService clientService;

  private final ClientRepository clientRepository;

  private final RestaurantRepository restaurantRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtUtil jwtUtil;

  @Autowired
  public AuthServiceImpl(ClientRepository clientRepository,
                         RestaurantRepository restaurantRepository,
                         JwtUtil jwtUtil,
                         PasswordEncoder passwordEncoder) {
    this.clientRepository = clientRepository;
    this.restaurantRepository = restaurantRepository;
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Client registerClient(ClientSignupDTO request){
    Client client = Client.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .cpf(request.getCpf())
        .phone(request.getPhone())
        .address(request.getAddress())
        .build();
    return clientRepository.save(client);
  }

  @Override
  public Restaurant registerRestaurant(RestaurantSignupDTO request) {
    Restaurant restaurant = Restaurant.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .cnpj(request.getCnpj())
        .category(request.getCategory())
        .phone(request.getPhone())
        .address(request.getAddress())
        .build();
    return restaurantRepository.save(restaurant);
  }

  @Override
  public String login(LoginDTO request) {
    String email = request.getEmail();
    String rawPassword = request.getPassword();
    UserType userType = request.getUserType();

    switch (userType){
      case CLIENT -> {
        Client client = clientRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        if (!passwordEncoder.matches(rawPassword, client.getPassword())){
          throw new BadCredentialsException("Credenciais inválidas");
        }
        return jwtUtil.generateToken(client.getEmail());
      }
      case RESTAURANT -> {
        Restaurant restaurant = restaurantRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));
        if (!passwordEncoder.matches(rawPassword, restaurant.getPassword())) {
          throw new BadCredentialsException("Credenciais inválidas");
        }
        return jwtUtil.generateToken(restaurant.getEmail());
      }
      default -> throw new RuntimeException("Tipo de usuário desconhecido");
    }
  }
}
