package com.example.solo.member.domain.dto.response;

import com.example.solo.member.domain.entity.Friend;

import lombok.Builder;

@Builder
public record FriendInfoResponse(Long friendId, Long toMemberId, String friendNickname) {

  public static FriendInfoResponse from(Friend friend) {
    return FriendInfoResponse.builder()
        .friendId(friend.getId())
        .toMemberId(friend.getToMember().getId())
        .friendNickname(friend.getToMember().getNickname())
        .build();
  }
}
