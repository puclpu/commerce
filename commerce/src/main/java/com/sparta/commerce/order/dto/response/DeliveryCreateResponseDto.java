package com.sparta.commerce.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DeliveryCreateResponseDto {

  private String name;
  private String phoneNumber;
  private String zipCode;
  private String address;
  private String message;

  public static DeliveryCreateResponseDto of(String name, String phoneNumber, String zipCode,
      String address, String message) {
    return DeliveryCreateResponseDto.builder()
        .name(name)
        .phoneNumber(phoneNumber)
        .zipCode(zipCode)
        .address(address)
        .message(message)
        .build();
  }
}
