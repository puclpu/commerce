package com.sparta.commerce.product.repository;

import com.sparta.commerce.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>,
    ProductImageQueryDSLRepository {

}
