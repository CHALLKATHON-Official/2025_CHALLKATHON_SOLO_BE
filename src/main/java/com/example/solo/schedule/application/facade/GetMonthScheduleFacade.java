package com.example.solo.schedule.application.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.solo.member.application.service.MemberQueryService;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.application.service.ScheduleQueryService;
import com.example.solo.schedule.domain.dto.response.GetScheduleResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetMonthScheduleFacade {

  private final ScheduleQueryService scheduleQueryService;
  private final MemberQueryService memberQueryService;

  public List<GetScheduleResponse> getSchedules(Long memberId, Integer year, Integer month) {
    Member member = memberQueryService.getMemberById(memberId);
    return scheduleQueryService.getSchedules(member, year, month);
  }
}
