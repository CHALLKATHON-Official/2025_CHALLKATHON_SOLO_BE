package com.example.solo.schedule.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

  @Query(
      """
                SELECT s
                FROM Schedule s
                WHERE  s.member.id = :memberId
                AND YEAR(s.date) = :year
                AND MONTH(s.date) = :month
                AND s.category = :category
               """)
  List<Schedule> findByYearAndMonthAndCategory(
      @Param("memberId") Long memberId,
      @Param("year") int year,
      @Param("month") int month,
      @Param("category") String category);
}
