package com.sparta.commerce.user.service;

import com.sparta.commerce.global.exception.CustomException;
import com.sparta.commerce.global.exception.ExceptionCode;
import com.sparta.commerce.global.security.service.EncryptService;
import com.sparta.commerce.user.domain.User;
import com.sparta.commerce.user.dto.request.SignUpRequestDto;
import com.sparta.commerce.user.dto.response.SignUpResponseDto;
import com.sparta.commerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final EncryptService encryptService;

  @Override
  public SignUpResponseDto signup(SignUpRequestDto requestDto) {
    // 이미 존재하는 회원인지 판별
    boolean isExist = findUserByEmail(requestDto.getEmail());
    if (isExist) {
      throw CustomException.from(ExceptionCode.MEMBER_EXISTS);
    }

    // 개인정보 암호화
    String password = passwordEncoder.encode(requestDto.getPassword());
    String email = encryptService.encrypt(requestDto.getEmail());
    String name = encryptService.encrypt(requestDto.getName());
    String phoneNumber = encryptService.encrypt(requestDto.getPhoneNumber());
    String zipCode = encryptService.encrypt(requestDto.getZipCode());
    String address = encryptService.encrypt(requestDto.getAddress());

    // 사용자 DB 저장
    User user = User.of(email, password, name, phoneNumber, zipCode, address, requestDto.getRole());
    userRepository.save(user);

    return SignUpResponseDto.from(user);
  }

  private boolean findUserByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

}
