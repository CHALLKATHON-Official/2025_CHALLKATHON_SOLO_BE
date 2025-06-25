package com.example.solo.member.domain.dto.response;

import com.example.solo.member.domain.entity.Friend;

import lombok.Builder;

@Builder
public record GetFriendRequestResponseDto(Long friendId, String fromMemberNickname) {

  public static GetFriendRequestResponseDto from(Friend friend) {
    return GetFriendRequestResponseDto.builder()
        .friendId(friend.getId())
        .fromMemberNickname(friend.getFromMember().getNickname())
        .build();
  }
}
