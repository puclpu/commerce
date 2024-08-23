package com.sparta.commerce.product.service;

import com.sparta.commerce.product.dto.ProductSummaryDto;
import com.sparta.commerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public Page<ProductSummaryDto> getProducts(String sortBy, int page, int size, boolean isAsc) {
    Pageable pageable = PageRequest.of(page, size);
    Page<ProductSummaryDto> products = productRepository.findProducts(pageable, sortBy, isAsc);
    return products;
  }
}
