package com.sparta.commerce.wish.dto.response;

import lombok.Getter;

@Getter
public class WishDto {

  private Long wishId;
  private Long productId;
  private String name;
  private String thumbnailImage;
  private int price;
  private int count;
  private String productOptionName;

}
