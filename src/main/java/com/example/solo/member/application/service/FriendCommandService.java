package com.example.solo.member.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.solo.member.domain.entity.Friend;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.repository.FriendRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendCommandService {

  private final FriendRepository friendRepository;

  public void createFriend(Member fromMember, Member toMember) {
    friendRepository.save(Friend.builder().fromMember(fromMember).toMember(toMember).build());
  }
}
