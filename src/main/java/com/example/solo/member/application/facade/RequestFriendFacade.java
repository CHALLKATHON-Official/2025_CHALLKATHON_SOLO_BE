package com.example.solo.member.application.facade;

import org.springframework.stereotype.Component;

import com.example.solo.member.application.service.FriendCommandService;
import com.example.solo.member.application.service.FriendQueryService;
import com.example.solo.member.application.service.MemberQueryService;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.domain.dto.request.RequestFriendRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RequestFriendFacade {

  private final MemberQueryService memberQueryService;
  private final FriendQueryService friendQueryService;
  private final FriendCommandService friendCommandService;

  public void requestFriend(Member fromMember, RequestFriendRequestDto requestDto) {
    Member targetMember = memberQueryService.getMemberByNickname(requestDto.nickname());
    Boolean isFriend = friendQueryService.isFriendByFromMemberToMember(fromMember, targetMember);
    if (!isFriend) {
      friendCommandService.createFriend(fromMember, targetMember, false);
    }
  }
}
