package com.sparta.commerce.order.dto.response;

import com.sparta.commerce.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderItemInfoDto {

  private Long orderItemId;
  private Long productId;
  private String name;
  private String thumbnailImage;
  private int quantity;
  private double price;

  public static OrderItemInfoDto from(OrderItem orderItem) {
    return OrderItemInfoDto.builder()
        .orderItemId(orderItem.getId())
        .productId(orderItem.getOptionItem().getProduct().getId())
        .name(orderItem.getOptionItem().getProduct().getName())
        .thumbnailImage(orderItem.getOptionItem().getProduct().getThumbnailImage())
        .quantity(orderItem.getQuantity())
        .price(orderItem.getPrice())
        .build();
  }
}
