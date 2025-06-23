package com.example.solo.schedule.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.solo.schedule.domain.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {}
