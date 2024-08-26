package com.sparta.commerce.order.repository;

import com.sparta.commerce.order.dto.response.OrderSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderQueryDSLRepository {

  Page<OrderSummaryDto> getOrders(Long userId, Pageable pageable);

}
