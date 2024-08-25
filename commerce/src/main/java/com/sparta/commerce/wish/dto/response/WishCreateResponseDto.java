package com.sparta.commerce.wish.dto.response;

import com.sparta.commerce.wish.entity.Wish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WishCreateResponseDto {

  private Long wishId;
  private Long userId;
  private Long optionItemId;
  private int quantity;

  public static WishCreateResponseDto from(Wish wish) {
    return WishCreateResponseDto.builder()
        .wishId(wish.getId())
        .userId(wish.getUser().getId())
        .optionItemId(wish.getOptionItem().getId())
        .quantity(wish.getQuantity())
        .build();
  }

}
