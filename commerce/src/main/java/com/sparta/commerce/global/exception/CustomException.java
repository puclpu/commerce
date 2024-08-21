package com.sparta.commerce.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

  @Getter
  private final HttpStatus status;

  public CustomException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public static CustomException from(ExceptionCode exceptionCode) {
    return new CustomException(exceptionCode.getMessage(), exceptionCode.getStatus());
  }

}
