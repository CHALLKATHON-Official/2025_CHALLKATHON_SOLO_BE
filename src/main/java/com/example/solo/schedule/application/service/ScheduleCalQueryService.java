package com.example.solo.schedule.application.service;

import org.springframework.stereotype.Service;

import com.example.solo.global.annotation.ReadOnlyTransactional;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.domain.entity.ScheduleCal;
import com.example.solo.schedule.domain.repository.ScheduleCalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ReadOnlyTransactional
public class ScheduleCalQueryService {

  private final ScheduleCalRepository scheduleCalRepository;

  public Boolean isScheduleCalExist(String category, Member member) {
    return scheduleCalRepository.existsByCategoryAndMember(category, member);
  }

  public ScheduleCal getScheduleCal(String category, Member member, Integer year, Integer month) {
    return scheduleCalRepository.findByCategoryAndMemberAndYearAndMonth(
        category, member, year, month);
  }
}
