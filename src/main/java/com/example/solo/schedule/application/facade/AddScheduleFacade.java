package com.example.solo.schedule.application.facade;

import org.springframework.stereotype.Component;

import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.application.service.ScheduleCalCommandService;
import com.example.solo.schedule.application.service.ScheduleCalQueryService;
import com.example.solo.schedule.application.service.ScheduleCommandService;
import com.example.solo.schedule.domain.dto.request.AddScheduleRequestDto;
import com.example.solo.schedule.domain.entity.Schedule;
import com.example.solo.schedule.domain.entity.ScheduleCal;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddScheduleFacade {

  private final ScheduleCommandService scheduleCommandService;
  private final ScheduleCalCommandService scheduleCalCommandService;
  private final ScheduleCalQueryService scheduleCalQueryService;

  public void addSchedule(Member member, AddScheduleRequestDto requestDto) {
    Schedule schedule = scheduleCommandService.createSchedule(member, requestDto);

    if (!requestDto.isPlan()) {
      if (!scheduleCalQueryService.isScheduleCalExist(requestDto.category(), member)) {
        scheduleCalCommandService.createScheduleCal(schedule);
      } else {
        ScheduleCal scheduleCal =
            scheduleCalQueryService.getScheduleCal(
                requestDto.category(),
                member,
                schedule.getDate().getYear(),
                schedule.getDate().getMonthValue());
        scheduleCalCommandService.updateScheduleCal(scheduleCal, schedule);
      }
    }
  }
}
