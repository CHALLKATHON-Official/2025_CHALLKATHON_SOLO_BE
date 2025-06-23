package com.example.solo.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.solo.global.annotation.SwaggerDocs;
import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.security.domain.MemberDetail;
import com.example.solo.schedule.application.facade.AddScheduleFacade;
import com.example.solo.schedule.controller.swaggerDocs.api.AddScheduleApiDocs;
import com.example.solo.schedule.domain.dto.request.AddScheduleRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

  private final AddScheduleFacade addScheduleFacade;

  @SwaggerDocs(AddScheduleApiDocs.class)
  @PostMapping()
  public ResponseEntity<BaseResponse<Void>> addSchedule(
      @AuthenticationPrincipal MemberDetail memberDetail,
      @RequestBody AddScheduleRequestDto requestDto) {
    addScheduleFacade.addSchedule(memberDetail.getMember(), requestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(BaseResponse.onSuccess(GlobalErrorCode.CREATED, null));
  }
}
