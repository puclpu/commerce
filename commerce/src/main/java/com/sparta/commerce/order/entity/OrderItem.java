package com.sparta.commerce.order.entity;

import com.sparta.commerce.product.entity.OptionItem;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_item_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "option_item_id")
  private OptionItem optionItem;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Builder
  public OrderItem(Order order, OptionItem optionItem, int quantity) {
    this.order = order;
    this.optionItem = optionItem;
    this.quantity = quantity;
  }

  public static OrderItem of(Order order, OptionItem optionItem, int quantity) {
    return OrderItem.builder()
        .order(order)
        .optionItem(optionItem)
        .quantity(quantity)
        .build();
  }
}
