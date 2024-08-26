package com.sparta.commerce.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

  // 401
  USER_MISMATCH(HttpStatus.UNAUTHORIZED, "등록자와 요청자가 일치하지 않습니다"),

  // 404
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
  PRODUCT_OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "상품 옵션을 찾을 수 없습니다."),
  OPTION_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "옵션 상품을 찾을 수 없습니다."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
  WISH_NOT_FOUND(HttpStatus.NOT_FOUND, "위시리스트에서 상품을 찾을 수 없습니다."),
  ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."),

  // 409
  USER_EXISTS(HttpStatus.CONFLICT, "해당 이메일의 회원이 이미 존재합니다."),
  WISH_EXISTS(HttpStatus.CONFLICT, "이미 위시리스트에 등록된 상품입니다."),
  OUT_OF_STOCK(HttpStatus.CONFLICT, "상품 재고가 부족합니다."),

  // 500
  CREATE_MAIL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "메일 생성에 실패했습니다."),
  ALGORITHM_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "지정된 알고리즘을 찾을 수 없습니다.");

  private final HttpStatus status;
  private final String message;
}
