package com.sparta.commerce.order.dto.request;

import lombok.Getter;

@Getter
public class OrderItemCreateRequestDto {

  private Long productId;
  private Long productOptionId;
  private int quantity;

}
