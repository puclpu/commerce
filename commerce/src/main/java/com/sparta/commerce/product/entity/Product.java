package com.sparta.commerce.product.entity;

import com.sparta.commerce.global.entity.Timestamped;
import com.sparta.commerce.product.type.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Timestamped {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Long id;

  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @Column(name = "price", nullable = false)
  private int price;

  @Column(name = "description", length = 500, nullable = false)
  private String description;

  @Column(name = "thumbnail_image", length = 512, nullable = false)
  private String thumbnailImage;

  @Column(name = "category", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Category category;

}
