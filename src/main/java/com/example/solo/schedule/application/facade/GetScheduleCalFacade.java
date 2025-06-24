package com.example.solo.schedule.application.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.application.service.ScheduleCalQueryService;
import com.example.solo.schedule.application.service.ScheduleQueryService;
import com.example.solo.schedule.domain.dto.response.GetScheduleCalResponse;
import com.example.solo.schedule.domain.dto.response.ScheduleCalResponse;
import com.example.solo.schedule.domain.dto.response.ScheduleResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetScheduleCalFacade {

  private final ScheduleCalQueryService scheduleCalQueryService;
  private final ScheduleQueryService scheduleQueryService;

  public GetScheduleCalResponse getScheduleCal(
      Member member, String category, Integer year, Integer month) {
    ScheduleCalResponse scheduleCalResponse =
        ScheduleCalResponse.from(
            scheduleCalQueryService.getScheduleCal(category, member, year, month));
    List<ScheduleResponse> scheduleResponseList =
        scheduleQueryService.getScheduleListByCategory(member, year, month, category).stream()
            .map(ScheduleResponse::from)
            .toList();

    return GetScheduleCalResponse.of(scheduleCalResponse, scheduleResponseList);
  }
}
