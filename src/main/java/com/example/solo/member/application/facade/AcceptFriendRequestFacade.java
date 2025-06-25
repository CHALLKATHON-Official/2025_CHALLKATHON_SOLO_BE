package com.example.solo.member.application.facade;

import org.springframework.stereotype.Component;

import com.example.solo.member.application.service.FriendCommandService;
import com.example.solo.member.application.service.FriendQueryService;
import com.example.solo.member.domain.dto.request.FriendAcceptRequestDto;
import com.example.solo.member.domain.entity.Friend;
import com.example.solo.member.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AcceptFriendRequestFacade {

  private final FriendQueryService friendQueryService;
  private final FriendCommandService friendCommandService;

  public void acceptFriendRequest(Member member, FriendAcceptRequestDto requestDto) {
    Friend friend = friendQueryService.getFriendById(requestDto.requestId());
    friendCommandService.createFriend(member, friend.getFromMember(), true);
    friendCommandService.setIsFriend(friend, true);
  }
}
