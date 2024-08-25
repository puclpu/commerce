package com.sparta.commerce.order.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
  PREPARING_PRODUCT("상품 준비 중"),
  PREPARING_DELIVERING("배송 준비 중"),
  DELIVERING("배송 중"),
  DELIVERED("배송 완료"),
  ORDER_CANCELED("주문 취소"),
  CANCELLATION_COMPLETED("취소 완료"),
  RETURN_REQUESTED("반품 신청"),
  RETURN_COMPLETED("반품 완료");

  private final String status;
}
