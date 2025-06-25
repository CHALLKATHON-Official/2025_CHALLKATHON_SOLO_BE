package com.example.solo.member.application.facade;

import org.springframework.stereotype.Component;

import com.example.solo.member.application.service.FriendQueryService;
import com.example.solo.member.domain.dto.response.FriendInfoResponse;
import com.example.solo.member.domain.dto.response.GetMyFriendListResponse;
import com.example.solo.member.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetMyFriendListFacade {

  private final FriendQueryService friendQueryService;

  public GetMyFriendListResponse getMemberFriendList(Member member) {

    return new GetMyFriendListResponse(
        friendQueryService.getFriendListByFromMemberAndIsFriendTrue(member).stream()
            .map(FriendInfoResponse::from)
            .toList());
  }
}
