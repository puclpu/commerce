package com.sparta.commerce.order.dto.response;

import com.sparta.commerce.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderItemCreateResponseDto {

  private Long orderItemId;
  private Long productId;
  private Long productOptionId;
  private int quantity;

  public static OrderItemCreateResponseDto from(OrderItem orderItem) {
    Long productOptionId = orderItem.getOptionItem().getProductOption() != null ?
        orderItem.getOptionItem().getProductOption().getId() : null;

    return OrderItemCreateResponseDto.builder()
        .orderItemId(orderItem.getId())
        .productId(orderItem.getOptionItem().getProduct().getId())
        .productOptionId(productOptionId)
        .quantity(orderItem.getQuantity())
        .build();
  }
}
