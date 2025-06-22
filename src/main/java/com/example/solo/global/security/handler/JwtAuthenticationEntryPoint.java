package com.example.solo.global.security.handler;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());

    BaseResponse<Object> errorResponse =
        BaseResponse.onFailure(GlobalErrorCode.AUTHENTICATION_REQUIRED, null);

    objectMapper.writeValue(response.getOutputStream(), errorResponse);

    response.getOutputStream().flush();
    response.getOutputStream().close();
  }
}
