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
  private Long productId;
  private Long productOptionId;
  private int count;

  public static WishCreateResponseDto from(Wish wish) {
    Long productOptionId = wish.getProductOption() != null ? wish.getProductOption().getId() : null;
    return WishCreateResponseDto.builder()
        .wishId(wish.getId())
        .userId(wish.getUser().getId())
        .productId(wish.getProduct().getId())
        .productOptionId(productOptionId)
        .count(wish.getCount())
        .build();
  }

}
