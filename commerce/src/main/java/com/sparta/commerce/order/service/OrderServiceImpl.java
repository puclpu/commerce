package com.sparta.commerce.order.service;

import com.sparta.commerce.global.exception.CustomException;
import com.sparta.commerce.global.exception.ExceptionCode;
import com.sparta.commerce.global.security.service.EncryptService;
import com.sparta.commerce.order.dto.request.OrderCreateRequestDto;
import com.sparta.commerce.order.dto.request.OrderItemCreateRequestDto;
import com.sparta.commerce.order.dto.response.DecryptedDeliveryInfo;
import com.sparta.commerce.order.dto.response.OrderCreateResponseDto;
import com.sparta.commerce.order.dto.response.OrderInfoDto;
import com.sparta.commerce.order.dto.response.OrderSummaryDto;
import com.sparta.commerce.order.entity.Delivery;
import com.sparta.commerce.order.entity.Order;
import com.sparta.commerce.order.entity.OrderItem;
import com.sparta.commerce.order.repository.DeliveryRepository;
import com.sparta.commerce.order.repository.OrderItemRepository;
import com.sparta.commerce.order.repository.OrderRepository;
import com.sparta.commerce.product.entity.OptionItem;
import com.sparta.commerce.product.repository.OptionItemRepository;
import com.sparta.commerce.user.entity.User;
import com.sparta.commerce.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final UserRepository userRepository;
  private final OptionItemRepository optionItemRepository;
  private final DeliveryRepository deliveryRepository;
  private final EncryptService encryptService;

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

      double price = calculatePrice(optionItem);

      OrderItem orderItem = OrderItem.of(order, optionItem, quantity, price);
      orderItems.add(orderItem);
    }

    // 배송 생성
    Delivery delivery = encodeDelivery(order, requestDto);

    // 주문 저장
    orderRepository.save(order);
    orderItemRepository.saveAll(orderItems);
    deliveryRepository.save(delivery);

    // 배송 복호화
    DecryptedDeliveryInfo decryptedDelivery = decodeDelivery(delivery);

    return OrderCreateResponseDto.of(order, orderItems, decryptedDelivery);
  }

  private double calculatePrice(OptionItem optionItem) {
    return optionItem.getProduct().getPrice();
  }

  @Override
  public Page<OrderSummaryDto> getOrders(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return orderRepository.getOrders(userId, pageable);
  }

  @Override
  public OrderInfoDto getOrder(Long orderId, Long userId) {
    // 주문 조회
    Order order = findOrder(orderId);

    // 주문 상품 조회
    List<OrderItem> orderItems = findOrderItemList(orderId);

    // 배송 정보 조회
    DecryptedDeliveryInfo decryptedDelivery = findDelivery(orderId);

    // 조회 요청자가 주문자인지 판별
    Long orderUserId = order.getUser().getId();
    hasPermissionForGetOrder(userId, orderUserId);
    String userName = encryptService.decrypt(order.getUser().getName());

    return OrderInfoDto.of(order, userName, orderItems, decryptedDelivery);
  }

  private void hasPermissionForGetOrder(Long userId, Long orderUserId) {
    if(!userId.equals(orderUserId)) {
      throw CustomException.from(ExceptionCode.USER_MISMATCH);
    }
  }

  private DecryptedDeliveryInfo findDelivery(Long orderId) {
    Delivery delivery = deliveryRepository.findByOrderId(orderId);
    return decodeDelivery(delivery);
  }

  private Order findOrder(Long orderId) {
    return orderRepository.findById(orderId)
        .orElseThrow(() -> CustomException.from(ExceptionCode.ORDER_NOT_FOUND));
  }

  private List<OrderItem> findOrderItemList(Long orderId) {
    return orderItemRepository.findAllByOrderId(orderId);
  }

  private DecryptedDeliveryInfo decodeDelivery(Delivery delivery) {
    String name = encryptService.decrypt(delivery.getName());
    String phoneNumber = encryptService.decrypt(delivery.getPhoneNumber());
    String zipCode = encryptService.decrypt(delivery.getZipCode());
    String address = encryptService.decrypt(delivery.getAddress());
    String message = encryptService.decrypt(delivery.getMessage());
    return DecryptedDeliveryInfo.of(delivery.getId(), name, phoneNumber, zipCode, address, message);
  }

  private Delivery encodeDelivery(Order order, OrderCreateRequestDto requestDto) {
    String name = encryptService.encrypt(requestDto.getName());
    String phoneNumber = encryptService.encrypt(requestDto.getPhoneNumber());
    String zipCode = encryptService.encrypt(requestDto.getZipCode());
    String address = encryptService.encrypt(requestDto.getAddress());
    String message = encryptService.encrypt(requestDto.getMessage());
    return Delivery.of(order, name, phoneNumber, zipCode, address, message);
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
