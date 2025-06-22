package com.example.solo.member.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.solo.member.domain.dto.request.SignUpRequestDto;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.entity.Password;
import com.example.solo.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthCommandService {

  private final MemberRepository memberRepository;

  public void createMember(SignUpRequestDto requestDto) {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    memberRepository.save(
        Member.builder()
            .email(requestDto.email())
            .password(Password.encrypt(requestDto.password(), encoder))
            .nickname(requestDto.nickname())
            .build());
  }
}
