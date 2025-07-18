package com.example.solo.schedule.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.solo.member.domain.entity.Member;
import com.example.solo.schedule.domain.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  @Query(
      """
            SELECT s
            FROM Schedule s
            WHERE  s.member.id = :memberId
            AND YEAR(s.date) = :year
            AND MONTH(s.date) = :month
           """)
  List<Schedule> findByYearAndMonth(
      @Param("memberId") Long memberId, @Param("year") int year, @Param("month") int month);

  Boolean existsByMemberAndCategoryAndDate(Member member, String category, LocalDate date);
}
