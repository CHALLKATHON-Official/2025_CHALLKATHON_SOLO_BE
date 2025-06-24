package com.example.solo.schedule.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.domain.entity.ScheduleCal;

public interface ScheduleCalRepository extends JpaRepository<ScheduleCal, Long> {
  Boolean existsByCategoryAndMember(String category, Member member);

  ScheduleCal findByCategoryAndMemberAndYearAndMonth(
      String category, Member member, Integer year, Integer month);
}
