package com.sparta.commerce.wish.service;

import com.sparta.commerce.global.exception.CustomException;
import com.sparta.commerce.global.exception.ExceptionCode;
import com.sparta.commerce.product.entity.Product;
import com.sparta.commerce.product.entity.ProductOption;
import com.sparta.commerce.product.repository.ProductOptionRepository;
import com.sparta.commerce.product.repository.ProductRepository;
import com.sparta.commerce.user.entity.User;
import com.sparta.commerce.user.repository.UserRepository;
import com.sparta.commerce.wish.dto.request.WishCreateRequestDto;
import com.sparta.commerce.wish.dto.response.WishCreateResponseDto;
import com.sparta.commerce.wish.dto.response.WishDto;
import com.sparta.commerce.wish.entity.Wish;
import com.sparta.commerce.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService{

  private final WishRepository wishRepository;
  private final ProductRepository productRepository;
  private final ProductOptionRepository productOptionRepository;
  private final UserRepository userRepository;

  @Override
  public WishCreateResponseDto createWish(WishCreateRequestDto requestDto, Long userId) {
    // 존재하는 회원인지 판별
    User user = findUser(userId);

    // 존재하는 상품인지 판별
    Product product = findProduct(requestDto.getProductId());

    // 이미 위시리스트에 동일한 상품을 담았는지 판별
    boolean isExist = findWishByUserAndProduct(user, product);
    if (isExist) {
      throw CustomException.from(ExceptionCode.WISH_EXISTS);
    }

    // 존재하는 상품 옵션인지 판별
    ProductOption productOption = null;
    if (requestDto.getProductOptionId() != null) {
      productOption = findProductOption(requestDto.getProductOptionId(), requestDto.getProductId());
    }

    // 위시 저장
    Wish wish = Wish.of(user, product, productOption, requestDto.getQuantity());
    wishRepository.save(wish);

    return WishCreateResponseDto.from(wish);
  }

  @Override
  public Page<WishDto> getWishList(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<WishDto> wishList = findWishList(userId, pageable);
    return wishList;
  }

  private Page<WishDto> findWishList(Long userId, Pageable pageable) {
    return wishRepository.findWishList(userId, pageable);
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.USER_NOT_FOUND));
  }

  private ProductOption findProductOption(Long productOptionId, Long productId) {
    return productOptionRepository.findByIdAndProductId(productOptionId, productId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.PRODUCT_OPTION_NOT_FOUND));
  }

  private Product findProduct(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.PRODUCT_NOT_FOUND));
  }

  private boolean findWishByUserAndProduct(User user, Product product) {
    return wishRepository.existsByUserAndProduct(user, product);
  }

}
