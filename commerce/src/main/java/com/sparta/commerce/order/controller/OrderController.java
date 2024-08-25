package com.sparta.commerce.order.controller;

import com.sparta.commerce.global.security.annotation.UserId;
import com.sparta.commerce.order.dto.request.OrderCreateRequestDto;
import com.sparta.commerce.order.dto.response.OrderCreateResponseDto;
import com.sparta.commerce.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderCreateResponseDto> createOrder(@UserId Long userId,
      @RequestBody OrderCreateRequestDto requestDto) {
    OrderCreateResponseDto responseDto = orderService.createOrder(userId, requestDto);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

}
