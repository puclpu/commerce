package com.sparta.commerce.product.repository;

import static com.sparta.commerce.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.product.dto.ProductSummaryDto;
import com.sparta.commerce.product.entity.Product;
import com.sparta.commerce.product.type.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryDSLRepositoryImpl implements ProductQueryDSLRepository{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Page<ProductSummaryDto> findProducts(Pageable pageable, String sortType, boolean isAsc,
      Category category) {
    OrderSpecifier orderSpecifier = createOrderSpecifier(sortType, isAsc);

    List<ProductSummaryDto> fetch =  jpaQueryFactory
        .select(Projections.fields(ProductSummaryDto.class,
                                        product.id.as("productId"),
                                        product.name,
                                        product.price,
                                        product.thumbnailImage))
        .from(product)
        .where(categoryEq(category))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(orderSpecifier)
        .fetch();

    JPQLQuery<Product> count = jpaQueryFactory.selectFrom(product);

    return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
  }

  private BooleanExpression categoryEq(Category category) {
    return category != null ? product.category.eq(category) : null;
  }

  private OrderSpecifier createOrderSpecifier (String sortType, boolean isAsc) {
    OrderSpecifier orderSpecifier;

    Order order = isAsc ? Order.ASC : Order.DESC;

    if (sortType.equalsIgnoreCase("price")) {
      orderSpecifier = new OrderSpecifier<>(order, product.price);
    } else if (sortType.equalsIgnoreCase("name")) {
      orderSpecifier = new OrderSpecifier<>(order, product.name);
    } else {
      orderSpecifier = new OrderSpecifier<>(order, product.id);
    }

    return orderSpecifier;
  }
}
