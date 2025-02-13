package com.rm.ifood_backend.model.complement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rm.ifood_backend.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "complements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complement {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private double price;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  @JsonBackReference
  private Product product;

}
