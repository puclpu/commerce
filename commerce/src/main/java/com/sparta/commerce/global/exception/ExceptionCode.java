package com.sparta.commerce.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

  // 404
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),

  // 409
  USER_EXISTS(HttpStatus.CONFLICT, "해당 이메일의 회원이 이미 존재합니다."),
  WISH_EXISTS(HttpStatus.CONFLICT, "이미 위시리스트에 등록된 상품입니다."),

  // 500
  CREATE_MAIL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "메일 생성에 실패했습니다."),
  ALGORITHM_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "지정된 알고리즘을 찾을 수 없습니다.");

  private final HttpStatus status;
  private final String message;
}
