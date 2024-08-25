package com.sparta.commerce.user.entity;

import com.sparta.commerce.global.entity.Timestamped;
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
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Timestamped {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "username", length = 30, nullable = false)
  private String username;

  @Column(name = "name", length = 64, nullable = false)
  private String name;

  @Column(name = "email", length = 192, nullable = false, unique = true)
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
  public User(String username, String name, String email, String password, String phoneNumber, String zipCode,
      String address, Role role) {
    this.username = username;
    this.name = name;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.zipCode = zipCode;
    this.address = address;
    this.role = role;
  }

  public static User of(String username, String email, String password, String name, String phoneNumber,
      String zipCode, String address, Role role) {
    return User.builder()
        .username(username)
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
