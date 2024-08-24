package com.sparta.commerce.wish.repository;

import com.sparta.commerce.product.entity.Product;
import com.sparta.commerce.user.entity.User;
import com.sparta.commerce.wish.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

  boolean existsByUserAndProduct(User user, Product product);

}
