package com.sparta.commerce.product.service;

import com.sparta.commerce.global.exception.CustomException;
import com.sparta.commerce.global.exception.ExceptionCode;
import com.sparta.commerce.product.dto.ProductImageInfoDto;
import com.sparta.commerce.product.dto.ProductInfoDto;
import com.sparta.commerce.product.dto.ProductOptionInfoDto;
import com.sparta.commerce.product.dto.ProductSummaryDto;
import com.sparta.commerce.product.entity.Product;
import com.sparta.commerce.product.repository.ProductImageRepository;
import com.sparta.commerce.product.repository.ProductOptionRepository;
import com.sparta.commerce.product.repository.ProductRepository;
import com.sparta.commerce.product.type.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductImageRepository productImageRepository;
  private final ProductOptionRepository productOptionRepository;

  @Override
  public Page<ProductSummaryDto> getProducts(String sortBy, int page, int size, boolean isAsc,
      Category category) {
    Sort.Direction direction = isAsc ? Direction.ASC : Direction.DESC;
    Sort sort = Sort.by(direction, sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<ProductSummaryDto> products = productRepository.findProducts(pageable, category);
    return products;
  }

  @Override
  public ProductInfoDto getProduct(Long productId) {
    Product product = findProduct(productId);
    List<ProductImageInfoDto> productImageList = findProductImageList(productId);
    List<ProductOptionInfoDto> productOptionList = findProductOptionList(productId);
    return ProductInfoDto.of(product, productImageList, productOptionList);
  }

  @Override
  public List<ProductOptionInfoDto> getProductOptions(Long productId) {
    return findProductOptionList(productId);
  }

  private List<ProductOptionInfoDto> findProductOptionList(Long productId) {
    return productOptionRepository.findProductOptionList(productId);
  }

  private List<ProductImageInfoDto> findProductImageList(Long productId) {
    return productImageRepository.findProductImageList(productId);
  }

  private Product findProduct(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.PRODUCT_NOT_FOUND));
  }
}
