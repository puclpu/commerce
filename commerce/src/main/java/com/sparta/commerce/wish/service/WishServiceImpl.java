package com.sparta.commerce.wish.service;

import com.sparta.commerce.global.exception.CustomException;
import com.sparta.commerce.global.exception.ExceptionCode;
import com.sparta.commerce.product.entity.OptionItem;
import com.sparta.commerce.product.repository.OptionItemRepository;
import com.sparta.commerce.user.entity.User;
import com.sparta.commerce.user.repository.UserRepository;
import com.sparta.commerce.wish.dto.request.WishCreateRequestDto;
import com.sparta.commerce.wish.dto.request.WishUpdateProductOptionRequestDto;
import com.sparta.commerce.wish.dto.request.WishUpdateQuantityRequestDto;
import com.sparta.commerce.wish.dto.response.WishCreateResponseDto;
import com.sparta.commerce.wish.dto.response.WishDto;
import com.sparta.commerce.wish.dto.response.WishUpdateProductOptionResponseDto;
import com.sparta.commerce.wish.dto.response.WishUpdateQuantityResponseDto;
import com.sparta.commerce.wish.entity.Wish;
import com.sparta.commerce.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService{

  private final WishRepository wishRepository;
  private final OptionItemRepository optionItemRepository;
  private final UserRepository userRepository;

  @Override
  public WishCreateResponseDto createWish(WishCreateRequestDto requestDto, Long userId) {
    // 존재하는 회원인지 판별
    User user = findUser(userId);

    // 존재하는 옵션 상품인지 판별
    OptionItem optionItem = findOptionItem(requestDto.getProductId(), requestDto.getProductOptionId());

    // 이미 위시리스트에 동일한 옵션 상품을 담았는지 판별
    boolean isExist = isExistsByWishByUserAndOrderItem(user, optionItem);
    if (isExist) {
      throw CustomException.from(ExceptionCode.WISH_EXISTS);
    }

    // 위시 저장
    Wish wish = Wish.of(user, optionItem, requestDto.getQuantity());
    wishRepository.save(wish);

    return WishCreateResponseDto.from(wish);
  }

  @Override
  public Page<WishDto> getWishList(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<WishDto> wishList = findWishList(userId, pageable);
    return wishList;
  }

  @Override
  @Transactional
  public WishUpdateQuantityResponseDto updateWishQuantity(Long wishId, Long userId,
      WishUpdateQuantityRequestDto requestDto) {
    Wish wish = findWishById(wishId);

    // 위시 생성자와 사용자가 동일한지 판별
    hasPermissionForWishUpdate(userId, wish.getUser().getId());

    // quantityChange 값 만큼 수량 변경
    wish.updateQuantity(requestDto.getQuantityChange());

    return WishUpdateQuantityResponseDto.from(wish);
  }

  @Override
  @Transactional
  public WishUpdateProductOptionResponseDto updateWishProductOption(Long wishId, Long userId,
      WishUpdateProductOptionRequestDto requestDto) {
    Wish wish = findWishById(wishId);

    // 위시 생성자와 사용자가 동일한지 판별
    hasPermissionForWishUpdate(userId, wish.getUser().getId());

    // 존재하는 옵션 상품인지 판별
    OptionItem optionItem = findOptionItem(requestDto.getProductId(), requestDto.getProductOptionId());

    // 상품 옵션 변경
    wish.updateOptionItem(optionItem);

    return WishUpdateProductOptionResponseDto.from(wish);
  }

  private void hasPermissionForWishUpdate(Long userId, Long wishUserId) {
    if(!userId.equals(wishUserId)) {
      throw CustomException.from(ExceptionCode.USER_MISMATCH);
    }
  }

  private Wish findWishById(Long wishId) {
    return wishRepository.findById(wishId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.WISH_NOT_FOUND));
  }

  private Page<WishDto> findWishList(Long userId, Pageable pageable) {
    return wishRepository.findWishList(userId, pageable);
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.USER_NOT_FOUND));
  }

  private OptionItem findOptionItem(Long productId, Long productOptionId) {
    return optionItemRepository.findByProductIdAndProductOptionId(productId, productOptionId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.OPTION_ITEM_NOT_FOUND));
  }

  private boolean isExistsByWishByUserAndOrderItem(User user, OptionItem optionItem) {
    return wishRepository.existsByUserAndOptionItem(user, optionItem);
  }

}
