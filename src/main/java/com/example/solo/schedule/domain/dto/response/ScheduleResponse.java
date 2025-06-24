package com.example.solo.schedule.domain.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.solo.schedule.domain.entity.Schedule;

import lombok.Builder;

@Builder
public record ScheduleResponse(String category, LocalTime time, LocalDate date) {

  public static ScheduleResponse from(Schedule schedule) {
    return ScheduleResponse.builder()
        .category(schedule.getCategory())
        .time(schedule.getTime())
        .date(schedule.getDate())
        .build();
  }
}
