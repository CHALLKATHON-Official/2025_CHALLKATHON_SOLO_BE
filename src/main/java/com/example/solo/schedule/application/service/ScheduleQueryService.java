package com.example.solo.schedule.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.solo.global.annotation.ReadOnlyTransactional;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.custom.ScheduleException;
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

  /**
   * 해당 년도 월에 존재하는 일정을 조회한다.
   *
   * @param member 해당 회원
   * @param year 조회 년도
   * @param month 조회 월
   * @return List<GetScheduleResponse>
   */
  public List<GetScheduleResponse> getSchedules(Member member, Integer year, Integer month) {
    List<Schedule> mothScheduleList =
        scheduleRepository.findByYearAndMonth(member.getId(), year, month);
    return mothScheduleList.stream().map(GetScheduleResponse::from).toList();
  }

  /**
   * 해당 날짜에 스캐줄이 있는지 확인한다.
   *
   * @param member 해당 회원
   * @param category 조회할 카테고리
   * @param date 조회할 날짜
   */
  public void checkIsSchedule(Member member, String category, LocalDate date) {
    if (scheduleRepository.existsByMemberAndCategoryAndDate(member, category, date)) {
      throw new ScheduleException(GlobalErrorCode.ALREADY_EXISTS_SCHEDULE);
    }
    ;
  }
}
