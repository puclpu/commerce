package com.sparta.commerce.wish.service;

import com.sparta.commerce.wish.dto.request.WishCreateRequestDto;
import com.sparta.commerce.wish.dto.request.WishUpdateProductOptionRequestDto;
import com.sparta.commerce.wish.dto.request.WishUpdateQuantityRequestDto;
import com.sparta.commerce.wish.dto.response.WishCreateResponseDto;
import com.sparta.commerce.wish.dto.response.WishDto;
import com.sparta.commerce.wish.dto.response.WishUpdateProductOptionResponseDto;
import com.sparta.commerce.wish.dto.response.WishUpdateQuantityResponseDto;
import org.springframework.data.domain.Page;

public interface WishService {

  WishCreateResponseDto createWish(WishCreateRequestDto requestDto, Long username);

  Page<WishDto> getWishList(Long userId, int page, int size);

  WishUpdateQuantityResponseDto updateWishQuantity(Long wishId, Long userId, WishUpdateQuantityRequestDto quantityChange);

  WishUpdateProductOptionResponseDto updateWishProductOption(Long wishId, Long userId, WishUpdateProductOptionRequestDto requestDto);
}
