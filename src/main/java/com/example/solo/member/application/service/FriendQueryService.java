package com.example.solo.member.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.solo.global.annotation.ReadOnlyTransactional;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.custom.FriendException;
import com.example.solo.member.domain.entity.Friend;
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

  public Friend getFriendById(Long friendId) {
    return friendRepository
        .findById(friendId)
        .orElseThrow(() -> new FriendException(GlobalErrorCode.NOT_FOUND_FRIEND_REQUEST));
  }

  public List<Friend> getFriendListByToMemberAndIsFriendFalse(Member member) {
    return friendRepository.findAllByToMemberAndIsFriendFalse(member);
  }

  public List<Friend> getFriendListByFromMemberAndIsFriendTrue(Member member) {
    return friendRepository.findAllByFromMemberAndIsFriendTrue(member);
  }
}
