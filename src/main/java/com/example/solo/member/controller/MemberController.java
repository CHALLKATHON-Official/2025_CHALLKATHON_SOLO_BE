package com.example.solo.member.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.security.domain.MemberDetail;
import com.example.solo.member.application.facade.AcceptFriendRequestFacade;
import com.example.solo.member.application.facade.GetFriendRequestFacade;
import com.example.solo.member.application.facade.GetMyFriendListFacade;
import com.example.solo.member.application.facade.RequestFriendFacade;
import com.example.solo.member.domain.dto.request.FriendAcceptRequestDto;
import com.example.solo.member.domain.dto.response.GetFriendRequestResponseDto;
import com.example.solo.member.domain.dto.response.GetMyFriendListResponse;
import com.example.solo.schedule.domain.dto.request.RequestFriendRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

  private final RequestFriendFacade requestFriendFacade;
  private final GetFriendRequestFacade getFriendRequestFacade;
  private final AcceptFriendRequestFacade acceptFriendRequestFacade;
  private final GetMyFriendListFacade getMyFriendListFacade;

  @PostMapping("/friend/request")
  public ResponseEntity<BaseResponse<Void>> requestFriend(
      @AuthenticationPrincipal MemberDetail memberDetail,
      @RequestBody RequestFriendRequestDto requestDto) {
    requestFriendFacade.requestFriend(memberDetail.getMember(), requestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(BaseResponse.onSuccess(GlobalErrorCode.CREATED, null));
  }

  @GetMapping("/friend/request")
  public ResponseEntity<BaseResponse<List<GetFriendRequestResponseDto>>> getFriendRequest(
      @AuthenticationPrincipal MemberDetail memberDetail) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            BaseResponse.onSuccess(
                GlobalErrorCode.OK,
                getFriendRequestFacade.getFriendRequest(memberDetail.getMember())));
  }

  @PostMapping("/friend/accept")
  public ResponseEntity<BaseResponse<Void>> acceptFriend(
      @AuthenticationPrincipal MemberDetail memberDetail,
      @RequestBody FriendAcceptRequestDto requestDto) {
    acceptFriendRequestFacade.acceptFriendRequest(memberDetail.getMember(), requestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(BaseResponse.onSuccess(GlobalErrorCode.CREATED, null));
  }

  @GetMapping("/my/friend")
  public ResponseEntity<BaseResponse<GetMyFriendListResponse>> getMyFriendList(
      @AuthenticationPrincipal MemberDetail memberDetail) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            BaseResponse.onSuccess(
                GlobalErrorCode.OK,
                getMyFriendListFacade.getMemberFriendList(memberDetail.getMember())));
  }
}
