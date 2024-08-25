package com.sparta.commerce.product.entity;

import com.sparta.commerce.global.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption extends Timestamped {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_option_id")
  private Long id;

  @Column(name = "name", length = 50, nullable = false)
  private String name;

}
