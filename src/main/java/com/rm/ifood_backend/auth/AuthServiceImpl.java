package com.rm.ifood_backend.auth;

import com.rm.ifood_backend.model.JwtResponseDTO;
import com.rm.ifood_backend.model.LoginDTO;
import com.rm.ifood_backend.model.client.ClientSignupDTO;
import com.rm.ifood_backend.model.restaurant.RestaurantSignupDTO;
import com.rm.ifood_backend.enums.UserType;
import com.rm.ifood_backend.model.client.Client;
import com.rm.ifood_backend.model.restaurant.Restaurant;
import com.rm.ifood_backend.repository.ClientRepository;
import com.rm.ifood_backend.repository.RestaurantRepository;
import com.rm.ifood_backend.service.ClientService;
import com.rm.ifood_backend.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        .name(request.name())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .cpf(request.cpf())
        .phone(request.phone())
        .address(request.address())
        .build();
    return clientRepository.save(client);
  }

  @Override
  public Restaurant registerRestaurant(RestaurantSignupDTO request) {
    Restaurant restaurant = Restaurant.builder()
        .name(request.name())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .cnpj(request.cnpj())
        .category(request.category())
        .phone(request.phone())
        .address(request.address())
        .build();
    return restaurantRepository.save(restaurant);
  }

  @Override
  public JwtResponseDTO login(LoginDTO request) {
    String email = request.email();
    String rawPassword = request.password();
    UserType userType = request.userType();

    switch (userType){
      case CLIENT -> {
        Client client = clientRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        if (!passwordEncoder.matches(rawPassword, client.getPassword())){
          throw new BadCredentialsException("Credenciais inválidas");
        }

        UUID id = client.getId();
        String token = jwtUtil.generateToken(client.getEmail());

        return new JwtResponseDTO(id, token);
      }
      case RESTAURANT -> {
        Restaurant restaurant = restaurantRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));
        if (!passwordEncoder.matches(rawPassword, restaurant.getPassword())) {
          throw new BadCredentialsException("Credenciais inválidas");
        }

        UUID id = restaurant.getId();
        String token = jwtUtil.generateToken(restaurant.getEmail());

        return new JwtResponseDTO(id, token);
      }
      default -> throw new RuntimeException("Tipo de usuário desconhecido");
    }
  }
}
