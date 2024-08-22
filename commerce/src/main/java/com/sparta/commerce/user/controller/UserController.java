package com.sparta.commerce.user.controller;

import com.sparta.commerce.user.dto.request.SignUpRequestDto;
import com.sparta.commerce.user.dto.response.SignUpResponseDto;
import com.sparta.commerce.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<SignUpResponseDto> signup(@RequestBody @Valid SignUpRequestDto requestDto) {
    log.info("회원 가입 요청");
    SignUpResponseDto responseDto = userService.signup(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

}
