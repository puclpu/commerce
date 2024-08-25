package com.sparta.commerce.order.dto.request;

import java.util.List;
import lombok.Getter;

@Getter
public class OrderCreateRequestDto {

  private List<OrderItemCreateRequestDto> orderItems;

}
