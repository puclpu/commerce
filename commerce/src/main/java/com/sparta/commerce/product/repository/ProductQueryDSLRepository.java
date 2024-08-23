package com.sparta.commerce.product.repository;

import com.sparta.commerce.product.dto.ProductSummaryDto;
import com.sparta.commerce.product.type.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryDSLRepository {

  Page<ProductSummaryDto> findProducts(Pageable pageable, String sortBy, boolean isAsc,
      Category category);

}
