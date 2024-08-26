package com.sparta.commerce.order.scheduler;

import com.sparta.commerce.order.service.OrderService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderScheduler {

  private final OrderService orderService;

  @Scheduled(cron = "0 0 0 * * *") // 매일 자정
  public void updateOrderStatus() {

    LocalDateTime now = LocalDateTime.now();
    orderService.updateOrderStatusDelivery(now);
    orderService.updateOrderStatusReturn(now);
  }

}
