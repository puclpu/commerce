package com.sparta.commerce.product.service;

import com.sparta.commerce.product.dto.ProductSummaryDto;
import com.sparta.commerce.product.type.Category;
import org.springframework.data.domain.Page;

public interface ProductService {

  Page<ProductSummaryDto> getProducts(String sortBy, int page, int size, boolean isAsc,
      Category category);

}
