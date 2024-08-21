package com.sparta.commerce.user.domain;

import com.sparta.commerce.user.type.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "name", length = 64, nullable = false)
  private String name;

  @Column(name = "email", length = 192, nullable = false)
  private String email;

  @Column(name = "password", length = 60, nullable = false)
  private String password;

  @Column(name = "phone_number", length = 128, nullable = false)
  private String phoneNumber;

  @Column(name = "zip_code", length = 64, nullable = false)
  private String zipCode;

  @Column(name = "address", length = 255, nullable = false)
  private String address;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  public User(String name, String email, String password, String phoneNumber, String zipCode,
      String address, Role role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.zipCode = zipCode;
    this.address = address;
    this.role = role;
  }

  public static User of(String email, String password, String name, String phoneNumber,
      String zipCode, String address, Role role) {
    return User.builder()
        .email(email)
        .password(password)
        .name(name)
        .phoneNumber(phoneNumber)
        .zipCode(zipCode)
        .address(address)
        .role(role)
        .build();
  }
}
