package com.sparta.commerce.product.repository;

import com.sparta.commerce.product.dto.ProductOptionInfoDto;
import java.util.List;

public interface ProductOptionQueryDSLRepository {

  List<ProductOptionInfoDto> findProductOptionList(Long productId);

}
