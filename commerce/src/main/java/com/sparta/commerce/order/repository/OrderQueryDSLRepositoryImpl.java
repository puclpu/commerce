package com.sparta.commerce.order.repository;

import static com.sparta.commerce.order.entity.QOrder.order;
import static com.sparta.commerce.order.entity.QOrderItem.orderItem;
import static com.sparta.commerce.product.entity.QOptionItem.optionItem;
import static com.sparta.commerce.product.entity.QProduct.product;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.order.dto.response.OrderSummaryDto;
import com.sparta.commerce.order.entity.Order;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryDSLRepositoryImpl implements OrderQueryDSLRepository{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Page<OrderSummaryDto> getOrders(Long userId, Pageable pageable) {
    List<OrderSummaryDto> fetch = jpaQueryFactory.select(Projections.fields(OrderSummaryDto.class,
            order.id.as("orderId"),
            product.name.min().as("productName"),
            orderItem.quantity.sum().as("quantity"),
            orderItem.quantity.multiply(orderItem.price).sum().as("price"),
            order.status,
            order.createdDateTime.as("orderDateTime")
            ))
        .from(orderItem)
        .leftJoin(optionItem).on(orderItem.optionItem.id.eq(optionItem.id))
        .leftJoin(product).on(optionItem.product.id.eq(product.id))
        .leftJoin(order).on(orderItem.order.id.eq(order.id))
        .where(order.user.id.eq(userId))
        .groupBy(order.id)
        .orderBy(order.createdDateTime.desc())
        .fetch();

    JPQLQuery<Order> count = jpaQueryFactory.selectFrom(order).where(order.user.id.eq(userId));

    return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
  }
}
