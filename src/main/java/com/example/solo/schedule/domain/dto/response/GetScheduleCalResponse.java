package com.example.solo.schedule.domain.dto.response;

import java.util.List;

public record GetScheduleCalResponse(
    ScheduleCalResponse scheduleCalResponse, List<ScheduleResponse> scheduleResponseList) {

  public static GetScheduleCalResponse of(
      ScheduleCalResponse scheduleCalResponse, List<ScheduleResponse> scheduleResponseList) {
    return new GetScheduleCalResponse(scheduleCalResponse, scheduleResponseList);
  }
}
