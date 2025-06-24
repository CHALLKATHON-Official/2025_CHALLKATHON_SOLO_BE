package com.example.solo.schedule.domain.dto.response;

import java.time.LocalDate;

import com.example.solo.schedule.domain.entity.Schedule;

import lombok.Builder;

@Builder
public record GetScheduleResponse(Float duration, LocalDate date, String category, Boolean isPlan) {

  public static GetScheduleResponse from(Schedule schedule) {
    return GetScheduleResponse.builder()
        .duration(schedule.getTime().getHour() + (schedule.getTime().getMinute() / 60.0f))
        .date(schedule.getDate())
        .category(schedule.getCategory())
        .isPlan(schedule.getScheduleIsPlan())
        .build();
  }
}
