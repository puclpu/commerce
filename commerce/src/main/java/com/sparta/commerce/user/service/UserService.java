package com.sparta.commerce.user.service;

import com.sparta.commerce.user.dto.request.SignUpRequestDto;
import com.sparta.commerce.user.dto.response.SignUpResponseDto;

public interface UserService {

  SignUpResponseDto signup(SignUpRequestDto requestDto);

}
