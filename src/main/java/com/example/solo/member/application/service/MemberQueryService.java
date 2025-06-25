package com.example.solo.member.application.service;

import org.springframework.stereotype.Service;

import com.example.solo.global.annotation.ReadOnlyTransactional;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.custom.MemberException;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ReadOnlyTransactional
public class MemberQueryService {

  private final MemberRepository memberRepository;

  public Member getMemberById(Long memberId) {
    return memberRepository
        .findById(memberId)
        .orElseThrow(() -> new MemberException(GlobalErrorCode.NOT_FOUND_MEMBER));
  }

  public Member getMemberByNickname(String nickname) {
    return memberRepository
        .findByNickname(nickname)
        .orElseThrow(() -> new MemberException(GlobalErrorCode.NOT_FOUND_MEMBER));
  }
}
