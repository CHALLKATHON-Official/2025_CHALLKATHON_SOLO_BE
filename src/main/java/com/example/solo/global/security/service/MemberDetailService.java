package com.example.solo.global.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.GlobalException;
import com.example.solo.global.security.domain.MemberDetail;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    Member member =
        memberRepository
            .findById(Long.parseLong(userId))
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    return new MemberDetail(member);
  }
}
