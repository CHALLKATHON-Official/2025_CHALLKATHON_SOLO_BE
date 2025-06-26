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

  /**
   * 주어진 데이터를 기반으로 로그인을 진행한다. 1.이메일을 통해 회원을 조회한다. 2.회원의 패스워드가 일치하는지 확인한다. 3.access Token을 생성한다.
   * 4.refresh Token을 생성한다.
   *
   * @param requestDto 로그인 데이터 {@link LoginRequestDto} (이메일, 비밀번호)
   * @return AuthTokenResponse {@link AuthTokenResponse} (AccessToken, RefreshToken)
   */
  public AuthTokenResponse login(LoginRequestDto requestDto) {

    Member member = authQueryService.getMemberByEmail(requestDto.email());
    member.getPassword().isSamePassword(requestDto.password(), encryptor);

    String accessToken = jwtTokenProvider.generateAccessToken(member.getId());
    String refreshToken = jwtTokenProvider.generateRefreshToken(member.getId());

    return new AuthTokenResponse(accessToken, refreshToken);
  }
}
