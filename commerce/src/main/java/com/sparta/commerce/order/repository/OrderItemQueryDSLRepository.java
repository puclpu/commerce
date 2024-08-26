package com.sparta.commerce.order.repository;

import com.sparta.commerce.order.entity.OrderItem;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemQueryDSLRepository {

  List<OrderItem> findReturnedOrderItems(LocalDateTime now);

}
