package com.sparta.commerce.wish.repository;

import static com.sparta.commerce.wish.entity.QWish.wish;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.wish.dto.response.WishDto;
import com.sparta.commerce.wish.entity.Wish;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WishQueryDSLRepositoryImpl implements WishQueryDSLRepository {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Page<WishDto> findWishList(Long userId, Pageable pageable) {
    List<WishDto> fetch = jpaQueryFactory
        .select(Projections.fields(WishDto.class,
            wish.id.as("wishId"),
            wish.product.id.as("productId"),
            wish.product.name,
            wish.product.thumbnailImage,
            wish.product.price,
            wish.quantity,
            wish.productOption.name.as("productOptionName")
            ))
        .from(wish)
        .leftJoin(wish.productOption)
        .where(wish.user.id.eq(userId))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(wish.id.desc())
        .fetch();

    JPQLQuery<Wish> count = jpaQueryFactory.selectFrom(wish).where(wish.user.id.eq(userId));

    return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
  }
}
