package com.sparta.commerce.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.commerce.global.security.service.UserDetailsImpl;
import com.sparta.commerce.global.security.util.JwtUtil;
import com.sparta.commerce.user.dto.request.LoginRequestDto;
import com.sparta.commerce.user.type.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final JwtUtil jwtUtil;

  public JwtAuthenticationFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
    setFilterProcessesUrl("/api/v1/users/login");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    log.info("로그인 시도");

    LoginRequestDto requestDto;
    try {
      requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
    } catch (IOException e) {
      throw new AuthenticationException("잘못된 입력 요청입니다.") {};
    }

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword(), null);

    return getAuthenticationManager().authenticate(token);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    log.info("로그인 성공 및 JWT 생성");
    String email = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
    Role role = ((UserDetailsImpl) authResult.getPrincipal()).getRole();

    String token = jwtUtil.createToken(email, role);
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    log.info("로그인 실패");
    response.setContentType("application/json;charset=UTF-8");
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> map = new HashMap<>();

    if (failed instanceof BadCredentialsException) { // 401 에러의 경우
      map.put("message", "아이디나 비밀번호가 일치하지 않습니다.");
      map.put("code", "401");
      map.put("error type", "Unauthorized");
    } else {
      map.put("message", failed.getMessage());
      map.put("code", "400");
      map.put("error type", "Bad Request");
    }
    mapper.writeValue(response.getWriter(), map);
  }
}
