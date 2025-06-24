package com.example.solo.schedule.domain.dto.request;

import java.time.LocalDate;

public record AddScheduleRequestDto(
    String category, Integer hour, Integer minutes, LocalDate date, Boolean isPlan) {}
