package com.sparta.commerce.user.dto.request;

import com.sparta.commerce.user.type.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

  @Email(message = "이메일 형식이 아닙니다")
  @Size(min = 1, max = 30)
  private String email;

  @Size(min = 1, max = 20)
  private String password;

  @Size(min = 1, max = 20)
  @Pattern(regexp = "^[\\d]*$", message = "전화번호는 숫자만 입력할 수 있습니다")
  private String phoneNumber;

  @Size(min = 1, max = 10)
  private String name;

  @Size(min = 1, max = 10)
  @Pattern(regexp = "^[\\d]*$", message = "우편번호는 숫자만 입력할 수 있습니다")
  private String zipCode;

  @Size(min = 1, max = 50)
  private String address;

  @NotNull(message = "권한은 필수 입력 사항입니다")
  private Role role;
}
