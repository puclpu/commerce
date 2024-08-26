package com.sparta.commerce.order.dto.response;

import com.sparta.commerce.order.entity.Order;
import com.sparta.commerce.order.entity.OrderItem;
import com.sparta.commerce.order.type.OrderStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderCreateResponseDto {

  private Long orderId;
  private Long userId;
  private OrderStatus status;
  private LocalDateTime createdDateTime;
  private List<OrderItemCreateResponseDto> orderItems;
  private DeliveryCreateResponseDto delivery;

  public static OrderCreateResponseDto of(Order order, List<OrderItem> orderItems,
      DecryptedDeliveryInfo decryptedDelivery) {
    List<OrderItemCreateResponseDto> orderItemCreateResponseDtoList = new ArrayList<>();
    for (OrderItem orderItem : orderItems) {
      OrderItemCreateResponseDto orderItemCreateResponseDto = OrderItemCreateResponseDto.from(orderItem);
      orderItemCreateResponseDtoList.add(orderItemCreateResponseDto);
    }

    DeliveryCreateResponseDto delivery = DeliveryCreateResponseDto.from(decryptedDelivery);

    return OrderCreateResponseDto.builder()
        .orderId(order.getId())
        .userId(order.getUser().getId())
        .status(order.getStatus())
        .createdDateTime(order.getCreatedDateTime())
        .orderItems(orderItemCreateResponseDtoList)
        .delivery(delivery)
        .build();
  }
}
