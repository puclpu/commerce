package com.sparta.commerce.wish.service;

import com.sparta.commerce.wish.dto.request.WishCreateRequestDto;
import com.sparta.commerce.wish.dto.response.WishCreateResponseDto;
import com.sparta.commerce.wish.dto.response.WishDto;
import org.springframework.data.domain.Page;

public interface WishService {

  WishCreateResponseDto createWish(WishCreateRequestDto requestDto, Long username);

  Page<WishDto> getWishList(Long userId, int page, int size);
}
