package com.example.solo.member.application.service;

import org.springframework.stereotype.Service;

import com.example.solo.global.annotation.ReadOnlyTransactional;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.repository.FriendRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ReadOnlyTransactional
public class FriendQueryService {

  private final FriendRepository friendRepository;

  public Boolean isFriendByFromMemberToMember(Member fromMember, Member toMember) {
    return friendRepository.existsByFromMemberAndToMember(fromMember, toMember);
  }
}
