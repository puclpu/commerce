package com.sparta.commerce.user.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class EmailRequestDto {

  @Email(message = "이메일 형식으로 입력해야 합니다")
  private String email;

}
