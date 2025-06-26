package com.example.solo.schedule.application.facade;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.application.service.ScheduleCommandService;
import com.example.solo.schedule.application.service.ScheduleQueryService;
import com.example.solo.schedule.domain.dto.request.AddScheduleRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddScheduleFacade {

  private final ScheduleCommandService scheduleCommandService;
  private final ScheduleQueryService scheduleQueryService;

  /**
   * 일정을 추가한다. 1. 오늘 일정이 추가되어 있는지 검증한다. 1-1. 오늘 일정이 있다면 예외를 발생시킨다. 2. 오늘 일정이 없다면 일정을 생성한다.
   *
   * @param member 일정 생성 회원
   * @param requestDto 일정 생성 데이터 {@link AddScheduleRequestDto} (카테고리, 시, 분)
   */
  public void addSchedule(Member member, AddScheduleRequestDto requestDto) {
    scheduleQueryService.checkIsSchedule(member, requestDto.category(), LocalDate.now());
    scheduleCommandService.createSchedule(member, requestDto);
  }
}
