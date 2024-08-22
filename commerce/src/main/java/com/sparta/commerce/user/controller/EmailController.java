package com.sparta.commerce.user.controller;

import com.sparta.commerce.user.dto.request.EmailRequestDto;
import com.sparta.commerce.user.dto.request.EmailVerificationRequestDto;
import com.sparta.commerce.user.dto.response.EmailResponseDto;
import com.sparta.commerce.user.dto.response.EmailVerificationResponseDto;
import com.sparta.commerce.user.service.EmailService;
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
@RequestMapping("/api/v1/users/email")
@RequiredArgsConstructor
public class EmailController {

  private final EmailService emailService;

  @PostMapping
  public ResponseEntity<EmailResponseDto> sendEmail(@RequestBody @Valid EmailRequestDto requestDto) {
    log.info("이메일 인증 요청");
    EmailResponseDto responseDto = emailService.sendEmail(requestDto);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @PostMapping("/verification")
  public ResponseEntity<EmailVerificationResponseDto> matchRandomCode(@RequestBody
      EmailVerificationRequestDto requestDto) {
    log.info("이메일 인증 코드 검증");
    EmailVerificationResponseDto responseDto = emailService.matchRandomCode(requestDto);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

}
