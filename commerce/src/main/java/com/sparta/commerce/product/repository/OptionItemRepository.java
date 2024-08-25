package com.sparta.commerce.product.repository;

import com.sparta.commerce.product.entity.OptionItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionItemRepository extends JpaRepository<OptionItem, Long> {

  Optional<OptionItem> findByProductIdAndProductOptionId(Long productId, Long productOptionId);
}
