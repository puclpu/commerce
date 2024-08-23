package com.sparta.commerce.product.repository;

import com.sparta.commerce.product.dto.ProductImageInfoDto;
import java.util.List;

public interface ProductImageQueryDSLRepository {

  List<ProductImageInfoDto> findProductImageList(Long productId);

}
