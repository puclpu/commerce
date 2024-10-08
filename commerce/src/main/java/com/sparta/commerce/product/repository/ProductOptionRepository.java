package com.sparta.commerce.product.repository;

import com.sparta.commerce.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long>, ProductOptionQueryDSLRepository {

}
