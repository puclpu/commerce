package com.sparta.commerce.order.service;

import com.sparta.commerce.order.dto.request.OrderCreateRequestDto;
import com.sparta.commerce.order.dto.response.OrderCreateResponseDto;
import com.sparta.commerce.order.dto.response.OrderSummaryDto;
import org.springframework.data.domain.Page;

public interface OrderService {

  OrderCreateResponseDto createOrder(Long userId, OrderCreateRequestDto requestDto);

  Page<OrderSummaryDto> getOrders(Long userId, int page, int size);
}
