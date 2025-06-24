package com.example.solo.schedule.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.solo.schedule.domain.entity.Schedule;
import com.example.solo.schedule.domain.entity.ScheduleCal;
import com.example.solo.schedule.domain.repository.ScheduleCalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleCalCommandService {

  private final ScheduleCalRepository scheduleCalRepository;

  public void createScheduleCal(Schedule schedule) {
    scheduleCalRepository.save(
        ScheduleCal.builder()
            .category(schedule.getCategory())
            .timeSum(schedule.getTime().getHour() + schedule.getTime().getMinute() / 60.0f)
            .average(schedule.getTime().getHour() + schedule.getTime().getMinute() / 60.0f)
            .year(schedule.getDate().getYear())
            .month(schedule.getDate().getMonthValue())
            .count(1)
            .member(schedule.getMember())
            .build());
  }

  public void updateScheduleCal(ScheduleCal scheduleCal, Schedule schedule) {
    scheduleCal.setTimeSum(
        scheduleCal.getTimeSum()
            + schedule.getTime().getHour()
            + schedule.getTime().getMinute() / 60.0f);
    scheduleCal.setCount(scheduleCal.getCount() + 1);
    scheduleCal.setAverage(scheduleCal.getTimeSum() / scheduleCal.getCount());
    scheduleCalRepository.save(scheduleCal);
  }
}
