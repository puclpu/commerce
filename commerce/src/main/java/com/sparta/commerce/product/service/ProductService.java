package com.sparta.commerce.product.service;

import com.sparta.commerce.product.dto.ProductInfoDto;
import com.sparta.commerce.product.dto.ProductOptionInfoDto;
import com.sparta.commerce.product.dto.ProductSummaryDto;
import com.sparta.commerce.product.type.Category;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {

  Page<ProductSummaryDto> getProducts(String sortBy, int page, int size, boolean isAsc,
      Category category);

  ProductInfoDto getProduct(Long productId);

  List<ProductOptionInfoDto> getProductOptions(Long productId);
}
