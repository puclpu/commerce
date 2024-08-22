package com.sparta.commerce.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmailResponseDto {

  private String code;

  public static EmailResponseDto from(String code) {
    return EmailResponseDto.builder()
        .code(code)
        .build();
  }
}
