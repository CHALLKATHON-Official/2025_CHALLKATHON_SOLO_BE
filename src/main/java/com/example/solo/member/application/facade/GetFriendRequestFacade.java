package com.example.solo.member.application.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.solo.member.application.service.FriendQueryService;
import com.example.solo.member.domain.dto.response.GetFriendRequestResponseDto;
import com.example.solo.member.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetFriendRequestFacade {

  private final FriendQueryService friendQueryService;

  public List<GetFriendRequestResponseDto> getFriendRequest(Member member) {
    return friendQueryService.getFriendListByToMemberAndIsFriendFalse(member).stream()
        .map(GetFriendRequestResponseDto::from)
        .toList();
  }
}
