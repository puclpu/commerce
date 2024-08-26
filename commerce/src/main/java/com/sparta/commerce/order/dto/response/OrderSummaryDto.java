package com.sparta.commerce.order.dto.response;

import com.sparta.commerce.order.type.OrderStatus;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class OrderSummaryDto {

  private Long orderId;
  private String productName;
  private int quantity;
  private int price;
  private OrderStatus status;
  private LocalDateTime orderDateTime;

}
