package com.sparta.commerce.product.repository;

import com.sparta.commerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryDSLRepository {

}
