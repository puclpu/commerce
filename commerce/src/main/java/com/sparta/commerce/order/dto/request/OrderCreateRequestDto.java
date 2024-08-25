package com.sparta.commerce.order.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderCreateRequestDto {

  private List<OrderItemCreateRequestDto> orderItems;

  @Size(min = 1, max = 10)
  private String name;

  @Size(min = 1, max = 20)
  @Pattern(regexp = "^[\\d]*$", message = "전화번호는 숫자만 입력할 수 있습니다")
  private String phoneNumber;

  @Size(min = 1, max = 10)
  @Pattern(regexp = "^[\\d]*$", message = "우편번호는 숫자만 입력할 수 있습니다")
  private String zipCode;

  @Size(min = 1, max = 50)
  private String address;

  @Size(max = 100)
  private String message;

}
