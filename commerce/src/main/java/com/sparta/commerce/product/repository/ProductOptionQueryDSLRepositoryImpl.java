package com.sparta.commerce.product.repository;

import static com.sparta.commerce.product.entity.QProductOption.productOption;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.product.dto.ProductOptionInfoDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductOptionQueryDSLRepositoryImpl implements ProductOptionQueryDSLRepository{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<ProductOptionInfoDto> findProductOptionList(Long productId) {
    return jpaQueryFactory.select(Projections.fields(ProductOptionInfoDto.class,
                                                      productOption.id.as("productOptionId"),
                                                      productOption.name))
        .from(productOption)
        .where(productOption.product.id.eq(productId))
        .fetch();
  }
}
