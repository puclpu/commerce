package com.sparta.commerce.order.dto.response;

import com.sparta.commerce.order.entity.Order;
import com.sparta.commerce.order.type.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderCancelResponseDto {

  private Long orderId;
  private OrderStatus status;

  public static OrderCancelResponseDto from(Order order) {
    return OrderCancelResponseDto.builder()
        .orderId(order.getId())
        .status(order.getStatus())
        .build();
  }
}
