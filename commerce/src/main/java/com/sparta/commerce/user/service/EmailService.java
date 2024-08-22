package com.sparta.commerce.user.service;

import com.sparta.commerce.user.dto.request.EmailRequestDto;
import com.sparta.commerce.user.dto.request.EmailVerificationRequestDto;
import com.sparta.commerce.user.dto.response.EmailResponseDto;
import com.sparta.commerce.user.dto.response.EmailVerificationResponseDto;

public interface EmailService {

  EmailResponseDto sendEmail(EmailRequestDto requestDto);

  EmailVerificationResponseDto matchRandomCode(EmailVerificationRequestDto requestDto);
}
