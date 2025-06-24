package com.example.solo.schedule.domain.dto.response;

import com.example.solo.schedule.domain.entity.ScheduleCal;

import lombok.Builder;

@Builder
public record ScheduleCalResponse(
    String category, Float timeSum, Float average, Integer year, Integer month) {

  public static ScheduleCalResponse from(ScheduleCal scheduleCal) {
    return ScheduleCalResponse.builder()
        .category(scheduleCal.getCategory())
        .timeSum(scheduleCal.getTimeSum())
        .average(scheduleCal.getAverage())
        .year(scheduleCal.getYear())
        .month(scheduleCal.getMonth())
        .build();
  }
}
