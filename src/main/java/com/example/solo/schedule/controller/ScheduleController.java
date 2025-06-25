package com.example.solo.schedule.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.solo.global.annotation.SwaggerDocs;
import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.security.domain.MemberDetail;
import com.example.solo.schedule.application.facade.AddScheduleFacade;
import com.example.solo.schedule.application.facade.GetMonthScheduleFacade;
import com.example.solo.schedule.application.facade.GetScheduleCalFacade;
import com.example.solo.schedule.controller.swaggerDocs.api.AddScheduleApiDocs;
import com.example.solo.schedule.controller.swaggerDocs.api.GetScheduleApiDocs;
import com.example.solo.schedule.controller.swaggerDocs.api.GetScheduleCalApiDocs;
import com.example.solo.schedule.domain.dto.request.AddScheduleRequestDto;
import com.example.solo.schedule.domain.dto.response.GetScheduleCalResponse;
import com.example.solo.schedule.domain.dto.response.GetScheduleResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

  private final AddScheduleFacade addScheduleFacade;
  private final GetMonthScheduleFacade getMonthScheduleFacade;
  private final GetScheduleCalFacade getScheduleCalFacade;

  @SwaggerDocs(AddScheduleApiDocs.class)
  @PostMapping()
  public ResponseEntity<BaseResponse<Void>> addSchedule(
      @AuthenticationPrincipal MemberDetail memberDetail,
      @RequestBody AddScheduleRequestDto requestDto) {
    addScheduleFacade.addSchedule(memberDetail.getMember(), requestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(BaseResponse.onSuccess(GlobalErrorCode.CREATED, null));
  }

  @SwaggerDocs(GetScheduleApiDocs.class)
  @GetMapping("/{memberId}")
  public ResponseEntity<BaseResponse<List<GetScheduleResponse>>> getSchedule(
      @PathVariable(name = "memberId") Long memberId,
      @RequestParam Integer year,
      @RequestParam Integer month) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            BaseResponse.onSuccess(
                GlobalErrorCode.OK, getMonthScheduleFacade.getSchedules(memberId, year, month)));
  }

  @SwaggerDocs(GetScheduleCalApiDocs.class)
  @GetMapping("/cal")
  public ResponseEntity<BaseResponse<GetScheduleCalResponse>> getScheduleCal(
      @AuthenticationPrincipal MemberDetail memberDetail,
      @RequestParam String category,
      @RequestParam Integer year,
      @RequestParam Integer month) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            BaseResponse.onSuccess(
                getScheduleCalFacade.getScheduleCal(
                    memberDetail.getMember(), category, year, month)));
  }
}
