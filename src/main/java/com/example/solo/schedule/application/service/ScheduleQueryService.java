package com.example.solo.schedule.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.solo.global.annotation.ReadOnlyTransactional;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.domain.dto.response.GetScheduleResponse;
import com.example.solo.schedule.domain.entity.Schedule;
import com.example.solo.schedule.domain.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ReadOnlyTransactional
public class ScheduleQueryService {

  private final ScheduleRepository scheduleRepository;

  public List<GetScheduleResponse> getSchedules(Member member, Integer year, Integer month) {
    List<Schedule> mothScheduleList =
        scheduleRepository.findByYearAndMonth(member.getId(), year, month);
    return mothScheduleList.stream().map(GetScheduleResponse::from).toList();
  }

  public List<Schedule> getScheduleListByCategory(
      Member member, Integer year, Integer month, String category) {
    return scheduleRepository.findByYearAndMonthAndCategory(member.getId(), year, month, category);
  }
}
