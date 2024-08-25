package com.sparta.commerce.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "option_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionItem {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "option_item_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_option_id")
  private ProductOption productOption;

  @Column(name = "stock", nullable = false)
  private int stock;

  public void deductStock(int quantity) {
    this.stock -= quantity;
  }
}
