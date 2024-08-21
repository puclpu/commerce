package com.sparta.commerce.user.dto.response;

import com.sparta.commerce.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponseDto {

  private Long userId;
  private String name;
  private String email;
  private String phoneNumber;
  private String zipCode;
  private String address;

  public static SignUpResponseDto from(User user) {
    return SignUpResponseDto.builder()
        .userId(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .zipCode(user.getZipCode())
        .address(user.getAddress())
        .build();
  }
}
