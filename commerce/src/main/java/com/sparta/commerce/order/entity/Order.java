package com.sparta.commerce.order.entity;

import com.sparta.commerce.global.entity.Timestamped;
import com.sparta.commerce.order.type.OrderStatus;
import com.sparta.commerce.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends Timestamped {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Builder
  public Order(User user, OrderStatus status) {
    this.user = user;
    this.status = status;
  }

  public static Order from(User user) {
    return Order.builder()
        .user(user)
        .status(OrderStatus.PREPARING_PRODUCT)
        .build();
  }
}
