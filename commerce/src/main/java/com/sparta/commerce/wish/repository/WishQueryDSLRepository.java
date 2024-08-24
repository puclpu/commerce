package com.sparta.commerce.wish.repository;

import com.sparta.commerce.wish.dto.response.WishDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WishQueryDSLRepository {

  Page<WishDto> findWishList(Long userId, Pageable pageable);
}
