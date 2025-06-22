package com.example.solo.global.security.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthExceptionHandlingFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (GlobalException e) {
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setStatus(HttpStatus.UNAUTHORIZED.value());

      BaseResponse<Object> errorResponse = BaseResponse.onFailure(e.getErrorCode(), null);

      objectMapper.writeValue(response.getOutputStream(), errorResponse);

      response.getOutputStream().flush();
      response.getOutputStream().close();
    }
  }
}
