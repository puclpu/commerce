package com.sparta.commerce.wish.repository;

import com.sparta.commerce.product.entity.OptionItem;
import com.sparta.commerce.user.entity.User;
import com.sparta.commerce.wish.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long>, WishQueryDSLRepository {

  boolean existsByUserAndOptionItem(User user, OptionItem optionItem);

}
