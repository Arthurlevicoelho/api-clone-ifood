package com.rm.ifood_backend.model.client;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rm.ifood_backend.model.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(unique = true)
  private String cpf;

  @Column(unique = true)
  private String phone;

  @Column(nullable = false)
  private String address;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
  @JsonManagedReference("client-orders")
  private List<Order> orders = new ArrayList<>();
}
