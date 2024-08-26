package com.sparta.commerce.order.controller;

import com.sparta.commerce.global.security.annotation.UserId;
import com.sparta.commerce.order.dto.request.OrderCreateRequestDto;
import com.sparta.commerce.order.dto.response.OrderCreateResponseDto;
import com.sparta.commerce.order.dto.response.OrderInfoDto;
import com.sparta.commerce.order.dto.response.OrderSummaryDto;
import com.sparta.commerce.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping
  public ResponseEntity<Page<OrderSummaryDto>> getOrders(@UserId Long userId,
                                                         @RequestParam("page")int page,
                                                         @RequestParam("size")int size) {
    Page<OrderSummaryDto> orderSummaryDtoPage = orderService.getOrders(userId, page-1, size);
    return ResponseEntity.status(HttpStatus.OK).body(orderSummaryDtoPage);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderInfoDto> getOrder(@PathVariable Long orderId, @UserId Long userId) {
    OrderInfoDto responseDto = orderService.getOrder(orderId, userId);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

}
