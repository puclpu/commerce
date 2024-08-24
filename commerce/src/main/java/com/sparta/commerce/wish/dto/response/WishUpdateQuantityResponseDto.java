package com.sparta.commerce.wish.dto.response;

import com.sparta.commerce.wish.entity.Wish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WishUpdateQuantityResponseDto {

  private Long wishId;
  private int quantity;

  public static WishUpdateQuantityResponseDto from(Wish wish) {
    return WishUpdateQuantityResponseDto.builder()
        .wishId(wish.getId())
        .quantity(wish.getQuantity())
        .build();
  }
}
