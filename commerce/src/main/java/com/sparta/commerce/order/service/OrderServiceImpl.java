package com.sparta.commerce.order.service;

import com.sparta.commerce.global.exception.CustomException;
import com.sparta.commerce.global.exception.ExceptionCode;
import com.sparta.commerce.order.dto.request.OrderCreateRequestDto;
import com.sparta.commerce.order.dto.request.OrderItemCreateRequestDto;
import com.sparta.commerce.order.dto.response.OrderCreateResponseDto;
import com.sparta.commerce.order.entity.Order;
import com.sparta.commerce.order.entity.OrderItem;
import com.sparta.commerce.order.repository.OrderItemRepository;
import com.sparta.commerce.order.repository.OrderRepository;
import com.sparta.commerce.product.entity.OptionItem;
import com.sparta.commerce.product.repository.OptionItemRepository;
import com.sparta.commerce.user.entity.User;
import com.sparta.commerce.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final UserRepository userRepository;
  private final OptionItemRepository optionItemRepository;

  @Override
  @Transactional
  public OrderCreateResponseDto createOrder(Long userId, OrderCreateRequestDto requestDto) {

    // 주문 가능한 회원인지 판별
    User user = findUser(userId);

    // 주문 생성
    Order order = Order.from(user);

    // 주문 아이템 생성
    List<OrderItem> orderItems = new ArrayList<>();
    List<OrderItemCreateRequestDto> orderItemCreateRequestDtoList = requestDto.getOrderItems();
    for (OrderItemCreateRequestDto orderItemCreateRequestDto : orderItemCreateRequestDtoList) {
      // 주문 가능한 상품인지 판별
      OptionItem optionItem = findOptionItem(orderItemCreateRequestDto.getProductId(),
          orderItemCreateRequestDto.getProductOptionId());

      // 재고 차감
      int quantity = orderItemCreateRequestDto.getQuantity();
      deductOptionItemStock(optionItem, quantity);

      OrderItem orderItem = OrderItem.of(order, optionItem, quantity);
      orderItems.add(orderItem);
    }

    // 주문 저장
    orderRepository.save(order);
    orderItemRepository.saveAll(orderItems);

    return OrderCreateResponseDto.of(order, orderItems);
  }

  private void deductOptionItemStock(OptionItem optionItem, int quantity) {
    optionItem.deductStock(quantity);
    if (optionItem.getStock() < 0) {
      throw CustomException.from(ExceptionCode.OUT_OF_STOCK);
    }
  }

  private OptionItem findOptionItem(Long productId, Long productOptionId) {
    return optionItemRepository.findByProductIdAndProductOptionId(productId, productOptionId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.OPTION_ITEM_NOT_FOUND));
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.USER_NOT_FOUND));
  }
}
