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
public class OrderInfoDto {

  private Long orderId;
  private String name;
  private LocalDateTime orderDateTime;
  private OrderStatus status;
  private List<OrderItemInfoDto> orderItems;
  private DeliveryInfoDto delivery;

  public static OrderInfoDto of(Order order, String userName, List<OrderItem> orderItems,
      DecryptedDeliveryInfo decryptedDelivery) {

    List<OrderItemInfoDto> orderItemInfoDtoList = new ArrayList<>();
    for (OrderItem orderItem : orderItems) {
      OrderItemInfoDto orderItemInfoDto = OrderItemInfoDto.from(orderItem);
      orderItemInfoDtoList.add(orderItemInfoDto);
    }

    DeliveryInfoDto deliveryInfoDto = DeliveryInfoDto.from(decryptedDelivery);

    return OrderInfoDto.builder()
        .orderId(order.getId())
        .name(userName)
        .orderDateTime(order.getCreatedDateTime())
        .status(order.getStatus())
        .orderItems(orderItemInfoDtoList)
        .delivery(deliveryInfoDto)
        .build();
  }
}
