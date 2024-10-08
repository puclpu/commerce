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

  public static DeliveryCreateResponseDto from(DecryptedDeliveryInfo decryptedDelivery) {
    return DeliveryCreateResponseDto.builder()
        .name(decryptedDelivery.getName())
        .phoneNumber(decryptedDelivery.getPhoneNumber())
        .zipCode(decryptedDelivery.getZipCode())
        .address(decryptedDelivery.getAddress())
        .message(decryptedDelivery.getMessage())
        .build();
  }
}
