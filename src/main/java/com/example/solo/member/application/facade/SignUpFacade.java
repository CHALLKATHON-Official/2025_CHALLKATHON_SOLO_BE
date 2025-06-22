package com.example.solo.member.application.facade;

import org.springframework.stereotype.Component;

import com.example.solo.member.application.service.AuthCommandService;
import com.example.solo.member.application.service.AuthQueryService;
import com.example.solo.member.domain.dto.request.SignUpRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SignUpFacade {

  private final AuthCommandService authCommandService;
  private final AuthQueryService authQueryService;

  /**
   * 주어진 요청 데이터를 기반으로 새로운 회원을 생성한다. 1. 중복되는 이메일이 존재하는지 확인한다. 2. 중복되는 닉네임이 존재하는지 확인한다. 3. 회원을 생성한다.
   *
   * @param requestDto 회원 생성 데이터 {@link SignUpRequestDto} (회원 이메일, 회원 비밀번호, 회원 닉네님)
   */
  public void signUp(SignUpRequestDto requestDto) {
    authQueryService.isSameEmail(requestDto.email());
    authQueryService.isSameNickname(requestDto.nickname());
    authCommandService.createMember(requestDto);
  }
}
