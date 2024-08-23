package com.sparta.commerce.product.dto;

import lombok.Getter;

@Getter
public class ProductSummaryDto {

  private Long productId;
  private String name;
  private int price;
  private String thumbnailImage;

}
