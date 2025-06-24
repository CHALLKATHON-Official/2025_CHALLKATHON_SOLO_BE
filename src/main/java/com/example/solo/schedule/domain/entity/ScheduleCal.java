package com.example.solo.schedule.domain.entity;

import jakarta.persistence.*;

import com.example.solo.member.domain.entity.Member;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ScheduleCal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "schedule_cal_id")
  private Long id;

  @Column(name = "schedule_cal_category")
  private String category;

  @Setter
  @Column(name = "schedule_cal_time_sum")
  private Float timeSum;

  @Setter
  @Column(name = "schedule_cal_average")
  private Float average;

  @Column(name = "schedule_cal_year")
  private Integer year;

  @Column(name = "schedule_cal_month")
  private Integer month;

  @Setter
  @Column(name = "schedule_cal_int")
  private Integer count;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;
}
