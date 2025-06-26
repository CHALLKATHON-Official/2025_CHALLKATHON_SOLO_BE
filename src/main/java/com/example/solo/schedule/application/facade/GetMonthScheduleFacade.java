package com.example.solo.schedule.application.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.application.service.ScheduleQueryService;
import com.example.solo.schedule.domain.dto.response.GetScheduleResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetMonthScheduleFacade {

  private final ScheduleQueryService scheduleQueryService;

  /**
   * 주어진 오청 데이터를 기반으로 일정을 조회한다. 1. 해당 년도의 월에 존재하는 일정 데이터를 조회한다.
   *
   * @param member 해당 회원
   * @param year 조회 년도
   * @param month 조회 월
   * @return List<GetScheduleResponse> 조회 데이터
   */
  public List<GetScheduleResponse> getSchedules(Member member, Integer year, Integer month) {
    return scheduleQueryService.getSchedules(member, year, month);
  }
}
