package com.example.solo.schedule.application.facade;

import org.springframework.stereotype.Component;

import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.application.service.ScheduleCommandService;
import com.example.solo.schedule.domain.dto.request.AddScheduleRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddScheduleFacade {

  private final ScheduleCommandService scheduleCommandService;

  public void addSchedule(Member member, AddScheduleRequestDto requestDto) {
    scheduleCommandService.createSchedule(member, requestDto);
  }
}
