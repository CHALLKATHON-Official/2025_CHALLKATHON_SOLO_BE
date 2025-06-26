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

  public void addSchedule(Member member, AddScheduleRequestDto requestDto) {
    scheduleQueryService.checkIsSchedule(member, requestDto.category(), LocalDate.now());
    scheduleCommandService.createSchedule(member, requestDto);
  }
}
