package com.sparta.commerce.wish.service;

import com.sparta.commerce.wish.dto.request.WishCreateRequestDto;
import com.sparta.commerce.wish.dto.response.WishCreateResponseDto;

public interface WishService {

  WishCreateResponseDto createWish(WishCreateRequestDto requestDto, Long username);

}
