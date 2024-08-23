package com.sparta.commerce.product.repository;

import static com.sparta.commerce.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.product.dto.ProductSummaryDto;
import com.sparta.commerce.product.entity.Product;
import com.sparta.commerce.product.type.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryDSLRepositoryImpl implements ProductQueryDSLRepository{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Page<ProductSummaryDto> findProducts(Pageable pageable,
      Category category) {
    OrderSpecifier[] osArr = createOrderSpecifier(pageable.getSort());

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
        .orderBy(osArr)
        .fetch();

    JPQLQuery<Product> count = jpaQueryFactory.selectFrom(product);

    return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
  }

  private BooleanExpression categoryEq(Category category) {
    return category != null ? product.category.eq(category) : null;
  }

  private OrderSpecifier[] createOrderSpecifier (Sort sort) {
    List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
    for (Sort.Order order : sort) {
      Order direction = order.isAscending() ? Order.ASC : Order.DESC; // 정렬 방향
      String property = order.getProperty(); // 정렬 속성
      PathBuilder orderByExpression = new PathBuilder(Product.class, "product");
      OrderSpecifier orderSpecifier = new OrderSpecifier<>(direction, orderByExpression.get(property));
      orderSpecifiers.add(orderSpecifier);
    }

    OrderSpecifier[] osArr = new OrderSpecifier[orderSpecifiers.size()];
    for (int i = 0; i < osArr.length; i++) {
      osArr[i] = orderSpecifiers.get(i);
    }

    return osArr;
  }
}
