package com.sparta.commerce.wish.entity;

import com.sparta.commerce.product.entity.OptionItem;
import com.sparta.commerce.user.entity.User;
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

@Getter
@Entity
@Table(name = "wishes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "wish_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "option_item_id")
  private OptionItem optionItem;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Builder
  public Wish(User user, OptionItem optionItem, int quantity) {
    this.user = user;
    this.optionItem = optionItem;
    this.quantity = quantity;
  }

  public static Wish of(User user, OptionItem optionItem, int quantity) {
    return Wish.builder()
        .user(user)
        .optionItem(optionItem)
        .quantity(quantity)
        .build();
  }

  public void updateQuantity(int quantityChange) {
    this.quantity = this.quantity + quantityChange;
    if (quantity < 1) { // 최소 수량 1
      this.quantity = 1;
    }
  }

  public void updateOptionItem(OptionItem optionItem) {
    this.optionItem = optionItem;
  }
}
