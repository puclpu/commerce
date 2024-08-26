package com.sparta.commerce.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DeliveryInfoDto {

  private Long deliveryId;
  private String name;
  private String phoneNumber;
  private String zipCode;
  private String address;
  private String message;

  public static DeliveryInfoDto from(DecryptedDeliveryInfo decryptedDelivery) {
    return DeliveryInfoDto.builder()
        .deliveryId(decryptedDelivery.getDeliveryId())
        .name(decryptedDelivery.getName())
        .phoneNumber(decryptedDelivery.getPhoneNumber())
        .zipCode(decryptedDelivery.getZipCode())
        .address(decryptedDelivery.getAddress())
        .message(decryptedDelivery.getMessage())
        .build();
  }
}
