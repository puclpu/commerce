package com.sparta.commerce.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmailVerificationResponseDto {

  private boolean certified;

  public static EmailVerificationResponseDto from(boolean certified) {
    return EmailVerificationResponseDto.builder()
        .certified(certified)
        .build();
  }
}
