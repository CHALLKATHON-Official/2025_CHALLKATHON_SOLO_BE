package com.example.solo.schedule.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

import com.example.solo.member.domain.entity.Member;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "schedule_id")
  private Long id;

  private String category;

  @Column(name = "time", columnDefinition = "TIME(0)")
  private LocalTime time;

  private LocalDate date;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;
}
