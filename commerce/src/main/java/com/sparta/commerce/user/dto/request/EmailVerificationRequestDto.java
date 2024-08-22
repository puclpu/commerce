package com.sparta.commerce.user.dto.request;

import lombok.Getter;

@Getter
public class EmailVerificationRequestDto {

  private String email;
  private String code;

}
