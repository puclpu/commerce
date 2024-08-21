package com.sparta.commerce.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

  MEMBER_EXISTS(HttpStatus.CONFLICT, "해당 이메일의 회원이 이미 존재합니다.");

  private final HttpStatus status;
  private final String message;
}
