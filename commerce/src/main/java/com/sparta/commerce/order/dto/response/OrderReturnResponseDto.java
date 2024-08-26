package com.sparta.commerce.order.dto.response;

import com.sparta.commerce.order.entity.Order;
import com.sparta.commerce.order.type.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderReturnResponseDto {

  private Long orderId;
  private OrderStatus status;

  public static OrderReturnResponseDto from(Order order) {
    return OrderReturnResponseDto.builder()
        .orderId(order.getId())
        .status(order.getStatus())
        .build();
  }
}
