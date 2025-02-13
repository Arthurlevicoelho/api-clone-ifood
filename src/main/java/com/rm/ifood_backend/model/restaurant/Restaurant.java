package com.rm.ifood_backend.model.restaurant;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rm.ifood_backend.model.order.Order;
import com.rm.ifood_backend.model.product.Product;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(unique = true, nullable = false)
  private String cnpj;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String address;

  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
  @JsonManagedReference("restaurant-orders")
  private List<Order> orders = new ArrayList<>();

  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Product> menu = new ArrayList<>();

}