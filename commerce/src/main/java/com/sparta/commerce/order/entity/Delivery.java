package com.sparta.commerce.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deliveries")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "delivery_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(name = "name", length = 64, nullable = false)
  private String name;

  @Column(name = "phone_number", length = 128, nullable = false)
  private String phoneNumber;

  @Column(name = "zip_code", length = 64, nullable = false)
  private String zipCode;

  @Column(name = "address", length = 255, nullable = false)
  private String address;

  @Column(name = "message", length = 304, nullable = false)
  private String message;

  @Column(name = "completed_date_time")
  private LocalDateTime completedDateTime;

  @Builder
  public Delivery(Order order, String name, String phoneNumber, String zipCode, String address,
      String message) {
    this.order = order;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.zipCode = zipCode;
    this.address = address;
    this.message = message;
  }

  public static Delivery of(Order order, String name, String phoneNumber, String zipCode, String address,
      String message) {
    return Delivery.builder()
        .order(order)
        .name(name)
        .phoneNumber(phoneNumber)
        .zipCode(zipCode)
        .address(address)
        .message(message)
        .build();
  }

}
