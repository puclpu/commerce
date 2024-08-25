package com.sparta.commerce.wish.dto.response;

import com.sparta.commerce.wish.entity.Wish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WishUpdateProductOptionResponseDto {

  private Long wishId;
  private String productOptionName;

  public static WishUpdateProductOptionResponseDto from(Wish wish) {
    return WishUpdateProductOptionResponseDto.builder()
        .wishId(wish.getId())
        .productOptionName(wish.getOptionItem().getProductOption().getName())
        .build();
  }
}
