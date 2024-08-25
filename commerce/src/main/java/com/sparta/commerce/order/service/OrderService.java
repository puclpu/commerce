package com.sparta.commerce.order.service;

import com.sparta.commerce.order.dto.request.OrderCreateRequestDto;
import com.sparta.commerce.order.dto.response.OrderCreateResponseDto;

public interface OrderService {

  OrderCreateResponseDto createOrder(Long userId, OrderCreateRequestDto requestDto);

}
