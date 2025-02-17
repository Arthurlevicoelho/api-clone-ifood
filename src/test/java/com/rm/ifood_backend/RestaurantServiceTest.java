//package com.rm.ifood_backend;
//
//import com.rm.ifood_backend.model.restaurant.Restaurant;
//import com.rm.ifood_backend.service.RestaurantService;
//import com.rm.ifood_backend.repository.RestaurantRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class RestaurantServiceTest {
//
//  @InjectMocks
//  private RestaurantService restaurantService;
//
//  @Mock
//  private RestaurantRepository restaurantRepository;
//
//  private UUID restaurantId;
//
//  private Restaurant restaurant;
//
//  private Restaurant restaurantUpdater;
//
//  @BeforeEach
//  void setUp() {
//    restaurantId = UUID.randomUUID();
//
//    restaurant = Restaurant.builder()
//        .id(restaurantId)
//        .name("Test Restaurant")
//        .email("restaurant@gmail.com")
//        .password("restaurant123@")
//        .cnpj("123456789082")
//        .category("Sobremesas")
//        .address("rua wzy tal")
//        .phone("85899654765")
//        .build();
//
//    restaurantUpdater = Restaurant.builder()
//        .id(restaurantId)
//        .name("Test Restaurant Updated")
//        .email("restaurant2@gmail.com")
//        .password("restaurant1234@")
//        .cnpj("123456789082")
//        .category("Almoço")
//        .address("rua wzy tal")
//        .phone("85899654765")
//        .build();
//  }
//
//  @Test
//  void shouldReturnAllRestaurants() {
//    when(restaurantRepository.findAll()).thenReturn(List.of(restaurant));
//
//    List<Restaurant> restaurants = restaurantService.getAll();
//
//    assertFalse(restaurants.isEmpty());
//    assertEquals(1, restaurants.size());
//
//    verify(restaurantRepository, times(1)).findAll();
//  }
//
//  @Test
//  void shouldFindRestaurantById() {
//    when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
//
//    Restaurant foundRestaurant = restaurantRepository.findById(restaurantId).orElse(null);
//
//    assertNotNull(foundRestaurant);
//    assertEquals(restaurantId, foundRestaurant.getId());
//
//    verify(restaurantRepository, times(1)).findById(restaurantId);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenRestaurantNotFound() {
//    when(restaurantRepository.existsById(restaurantId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> restaurantService.findById(restaurantId));
//
//    verify(restaurantRepository, times(1)).existsById(restaurantId);
//  }
//
//  @Test
//  void shouldCreateRestaurant() {
//    when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
//
//    Restaurant savedRestaurant = restaurantService.create(restaurant);
//
//    assertNotNull(savedRestaurant);
//    assertEquals(restaurantId, savedRestaurant.getId());
//
//    verify(restaurantRepository, times(1)).save(restaurant);
//  }
//
//  @Test
//  void shouldUpdateRestaurant() {
//    when(restaurantRepository.existsById(restaurantId)).thenReturn(true);
//    when(restaurantRepository.save(restaurantUpdater)).thenReturn(restaurantUpdater);
//
//    Restaurant updatedRestaurant = restaurantService.update(restaurantId, restaurantUpdater);
//
//    assertNotNull(updatedRestaurant);
//    assertEquals(restaurantId, updatedRestaurant.getId());
//
//    verify(restaurantRepository, times(1)).existsById(restaurantId);
//    verify(restaurantRepository, times(1)).save(restaurantUpdater);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenUpdatingNonExistentRestaurant() {
//    when(restaurantRepository.existsById(restaurantId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> restaurantService.update(restaurantId, restaurantUpdater));
//
//    verify(restaurantRepository, times(1)).existsById(restaurantId);
//    verify(restaurantRepository, never()).findById(any(UUID.class));
//    verify(restaurantRepository, never()).save(any(Restaurant.class));
//  }
//
//  @Test
//  void shouldDeleteRestaurant() {
//    when(restaurantRepository.existsById(restaurantId)).thenReturn(true);
//
//    doNothing().when(restaurantRepository).deleteById(restaurantId);
//
//    assertDoesNotThrow(() -> restaurantService.delete(restaurantId));
//
//    verify(restaurantRepository, times(1)).existsById(restaurantId);
//    verify(restaurantRepository, times(1)).deleteById(restaurantId);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenDeletingNonExistentRestaurant() {
//    when(restaurantRepository.existsById(restaurantId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> restaurantService.delete(restaurantId));
//
//    verify(restaurantRepository, times(1)).existsById(restaurantId);
//    verify(restaurantRepository, never()).deleteById(any(UUID.class));
//  }
//}
