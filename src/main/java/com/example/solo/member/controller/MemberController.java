package com.example.solo.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.security.domain.MemberDetail;
import com.example.solo.member.application.facade.RequestFriendFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

  private final RequestFriendFacade requestFriendFacade;

  @PostMapping("/{targetid}/friend/request")
  public ResponseEntity<BaseResponse<Void>> requestFriend(
      @AuthenticationPrincipal MemberDetail memberDetail,
      @PathVariable(name = "targetid") Long targetId) {
    requestFriendFacade.requestFriend(memberDetail.getMember(), targetId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(BaseResponse.onSuccess(GlobalErrorCode.CREATED, null));
  }
}
