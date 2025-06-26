package com.example.solo.member.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.solo.member.domain.dto.request.SignUpRequestDto;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.entity.Password;
import com.example.solo.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthCommandService {

  private final PasswordEncryptor encryptor;
  private final MemberRepository memberRepository;

  /**
   * 회원을 생성한다.
   *
   * @param requestDto {@link SignUpRequestDto} (이메일, 비밀번호 평문, 닉네임)
   */
  public void createMember(SignUpRequestDto requestDto) {

    memberRepository.save(
        Member.builder()
            .email(requestDto.email())
            .password(Password.encrypt(requestDto.password(), encryptor))
            .nickname(requestDto.nickname())
            .build());
  }
}
