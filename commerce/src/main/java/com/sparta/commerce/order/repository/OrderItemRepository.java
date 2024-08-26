package com.sparta.commerce.order.repository;

import com.sparta.commerce.order.entity.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemQueryDSLRepository {

  List<OrderItem> findAllByOrderId(Long orderId);

}
