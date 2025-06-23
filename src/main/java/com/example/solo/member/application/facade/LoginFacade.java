package com.example.solo.member.application.facade;

import org.springframework.stereotype.Component;

import com.example.solo.global.security.jwt.JwtTokenProvider;
import com.example.solo.member.application.service.AuthQueryService;
import com.example.solo.member.domain.dto.request.LoginRequestDto;
import com.example.solo.member.domain.dto.response.AuthTokenResponse;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;
import com.example.solo.member.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginFacade {

  private final AuthQueryService authQueryService;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncryptor encryptor;

  public AuthTokenResponse login(LoginRequestDto requestDto) {

    Member member = authQueryService.getMemberByEmail(requestDto.email());
    member.getPassword().isSamePassword(requestDto.password(), encryptor);

    String accessToken = jwtTokenProvider.generateAccessToken(member.getId());
    String refreshToken = jwtTokenProvider.generateRefreshToken(member.getId());

    return new AuthTokenResponse(accessToken, refreshToken);
  }
}
