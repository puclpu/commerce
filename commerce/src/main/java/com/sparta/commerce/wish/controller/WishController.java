package com.sparta.commerce.wish.controller;

import com.sparta.commerce.global.security.annotation.UserId;
import com.sparta.commerce.wish.dto.request.WishCreateRequestDto;
import com.sparta.commerce.wish.dto.response.WishCreateResponseDto;
import com.sparta.commerce.wish.service.WishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/wish")
@RequiredArgsConstructor
public class WishController {

  private final WishService wishService;

  @PostMapping("/api/v1/wish")
  public ResponseEntity<WishCreateResponseDto> createWish(@RequestBody @Valid WishCreateRequestDto requestDto,
      @UserId Long userId) {
    WishCreateResponseDto responseDto = wishService.createWish(requestDto, userId);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

}
