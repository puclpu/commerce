package com.sparta.commerce.user.service;

import com.sparta.commerce.global.exception.CustomException;
import com.sparta.commerce.global.exception.ExceptionCode;
import com.sparta.commerce.global.security.service.EncryptService;
import com.sparta.commerce.user.dto.request.EmailRequestDto;
import com.sparta.commerce.user.dto.request.EmailVerificationRequestDto;
import com.sparta.commerce.user.dto.response.EmailResponseDto;
import com.sparta.commerce.user.dto.response.EmailVerificationResponseDto;
import com.sparta.commerce.user.repository.EmailRepository;
import com.sparta.commerce.user.repository.UserRepository;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final UserRepository userRepository;
  private final EmailRepository emailRepository;
  private final JavaMailSender javaMailSender;
  private final EncryptService encryptService;

  @Override
  public EmailResponseDto sendEmail(EmailRequestDto requestDto) {
    // 이미 존재하는 회원인지 판별
    boolean isExist = findUserByEmail(requestDto.getEmail());
    if (isExist) {
      throw CustomException.from(ExceptionCode.MEMBER_EXISTS);
    }

    // 인증 코드 생성
    String code = createRandomCode();

    // redis 저장
    emailRepository.setValue(requestDto.getEmail(), code, Duration.ofMinutes(3));

    // 메일 발송
    MimeMessage mimeMessage =  createMail(requestDto, code);
    javaMailSender.send(mimeMessage);

    return EmailResponseDto.from(code);
  }

  @Override
  public EmailVerificationResponseDto matchRandomCode(EmailVerificationRequestDto requestDto) {
    String code = emailRepository.getValueByKey(requestDto.getEmail());

    boolean certified = code != null && code.matches(requestDto.getCode());

    return EmailVerificationResponseDto.from(certified);
  }

  private boolean findUserByEmail(String email) {
    String encodedEmail = encryptService.encrypt(email);
    return userRepository.existsByEmail(encodedEmail);
  }

  private MimeMessage createMail(EmailRequestDto requestDto, String code) {
    MimeMessage message = javaMailSender.createMimeMessage();
    try {
      message.addRecipients(RecipientType.TO, requestDto.getEmail());
      message.setSubject("[이메일 인증 요청]");
      message.setText(code);
    } catch (MessagingException e) {
      throw CustomException.from(ExceptionCode.CREATE_MAIL_FAILED);
    }
    return message;
  }

  private String createRandomCode() {
    Random random;
    try {
      random = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      throw CustomException.from(ExceptionCode.ALGORITHM_NOT_FOUND);
    }

    StringBuilder randomCode = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      int randomNumber = random.nextInt(10); // 0-9
      randomCode.append(randomNumber);
    }

    return randomCode.toString();
  }

}
