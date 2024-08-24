package com.sparta.commerce.product.dto;

import com.sparta.commerce.product.entity.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductInfoDto {

  private Long productId;
  private String name;
  private int price;
  private String description;
  private List<ProductImageInfoDto> productImages;
  private List<ProductOptionInfoDto> productOptions;

  public static ProductInfoDto of(Product product, List<ProductImageInfoDto> productImageList,
      List<ProductOptionInfoDto> productOptionList) {
    return ProductInfoDto.builder()
        .productId(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .description(product.getDescription())
        .productImages(productImageList)
        .productOptions(productOptionList)
        .build();
  }
}
