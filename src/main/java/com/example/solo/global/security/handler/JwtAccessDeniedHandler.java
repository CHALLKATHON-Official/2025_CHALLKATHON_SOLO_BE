package com.example.solo.global.security.handler;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.FORBIDDEN.value());

    BaseResponse<Object> errorResponse =
        BaseResponse.onFailure(GlobalErrorCode.ACCESS_DENIED, null);

    objectMapper.writeValue(response.getOutputStream(), errorResponse);

    response.getOutputStream().flush();
    response.getOutputStream().close();
  }
}
