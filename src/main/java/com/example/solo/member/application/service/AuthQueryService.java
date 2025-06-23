package com.example.solo.member.application.service;

import org.springframework.stereotype.Service;

import com.example.solo.global.annotation.ReadOnlyTransactional;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.custom.AuthException;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ReadOnlyTransactional
public class AuthQueryService {

  private final MemberRepository memberRepository;

  /**
   * 중복된 이메일이 존재하는지 확인한다.
   *
   * @param email 조회할 이메일
   * @throws AuthException (@Code GlobalException.ALREADY_EXISTS_EMAIL) - 이미 존재하는 이메일입니다.
   */
  public void isSameEmail(String email) {
    memberRepository
        .findByEmail(email)
        .ifPresent(
            member -> {
              throw new AuthException(GlobalErrorCode.ALREADY_EXISTS_EMAIL);
            });
  }

  /**
   * 중복된 닉네임이 존재하는지 확인합니다.
   *
   * @param nickname
   * @throws AuthException (@Code GlobalException.ALREADY_EXISTS_NICKNAME) - 이미 존재하는 닉네임입니다.
   */
  public void isSameNickname(String nickname) {
    memberRepository
        .findByNickname(nickname)
        .ifPresent(
            member -> {
              throw new AuthException(GlobalErrorCode.ALREADY_EXISTS_NICKNAME);
            });
  }

  public Member getMemberByEmail(String email) {
    return memberRepository
        .findByEmail(email)
        .orElseThrow(() -> new AuthException(GlobalErrorCode.NOT_FOUND_MEMBER));
  }
}
