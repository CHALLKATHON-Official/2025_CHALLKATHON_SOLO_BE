package com.example.solo.schedule.application.service;

import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.domain.dto.request.AddScheduleRequestDto;
import com.example.solo.schedule.domain.entity.Schedule;
import com.example.solo.schedule.domain.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleCommandService {

  private final ScheduleRepository scheduleRepository;

  public void createSchedule(Member member, AddScheduleRequestDto requestDto) {
    scheduleRepository.save(
        Schedule.builder()
            .category(requestDto.category())
            .time(LocalTime.of(requestDto.hour(), requestDto.minutes()))
            .date(requestDto.date())
            .scheduleIsPlan(requestDto.isPlan())
            .member(member)
            .build());
  }
}
