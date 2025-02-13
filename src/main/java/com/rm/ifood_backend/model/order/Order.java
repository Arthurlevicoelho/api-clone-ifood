package com.rm.ifood_backend.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rm.ifood_backend.enums.OrderStatus;
import com.rm.ifood_backend.model.product.Product;
import com.rm.ifood_backend.model.restaurant.Restaurant;
import com.rm.ifood_backend.model.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "client", nullable = false)
  @JsonBackReference("client-orders")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "restaurant", nullable = false)
  @JsonBackReference("restaurant-orders")
  private Restaurant restaurant;

  @ManyToMany
  @JoinTable(
      name = "order_products",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<Product> products;

  @Column(nullable = false)
  private double total_price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderStatus status;
}
