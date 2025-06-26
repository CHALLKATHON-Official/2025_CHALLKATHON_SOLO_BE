package com.example.solo.schedule.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.custom.ScheduleException;
import com.example.solo.member.domain.encrypt.BCryptPasswordEncryptor;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.entity.Password;
import com.example.solo.schedule.domain.dto.response.GetScheduleResponse;
import com.example.solo.schedule.domain.entity.Schedule;
import com.example.solo.schedule.domain.repository.ScheduleRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ScheduleQueryServiceTest {

  @Mock private ScheduleRepository scheduleRepository;

  @InjectMocks private ScheduleQueryService scheduleQueryService;

  private PasswordEncryptor encryptor;
  private Member member;

  private final String TEST_EMAIL = "test";
  private final String TEST_PASSWORD = "test1234!@#$";
  private final String TEST_NICKNAME = "test";
  private final String TEST_CATEGORY = "test";
  private final Integer TEST_YEAR = LocalDate.now().getYear();
  private final Integer TEST_MONTH = LocalDate.now().getMonthValue();
  private final Integer TEST_HOUR = 1;
  private final Integer TEST_MINUTES = 0;

  @BeforeEach
  void setUp() {
    encryptor = new BCryptPasswordEncryptor(new BCryptPasswordEncoder());

    member =
        Member.builder()
            .email(TEST_EMAIL)
            .password(Password.encrypt(TEST_PASSWORD, encryptor))
            .nickname(TEST_NICKNAME)
            .build();
  }

  @Test
  @DisplayName("해당 년도 월에 존재하는 일정을 조회한다.")
  void getSchedulesSuccess() {
    // give
    Schedule schedule =
        Schedule.builder()
            .id(1L)
            .member(member)
            .date(LocalDate.now())
            .time(LocalTime.of(TEST_HOUR, TEST_MINUTES))
            .category(TEST_CATEGORY)
            .build();

    when(scheduleRepository.findByYearAndMonth(member.getId(), TEST_YEAR, TEST_MONTH))
        .thenReturn(List.of(schedule));

    // when
    List<GetScheduleResponse> result =
        scheduleQueryService.getSchedules(member, TEST_YEAR, TEST_MONTH);

    // then
    assertEquals(1, result.size());
  }

  @Nested
  @DisplayName("해당 날짜에 스케줄이 있는지 조회한다.")
  class checkIsSchedule {

    @Test
    @DisplayName("없으면 진행한다.")
    void checkIsScheduleSuccess() {
      // give
      when(scheduleRepository.existsByMemberAndCategoryAndDate(
              member, TEST_CATEGORY, LocalDate.now()))
          .thenReturn(false);

      // when + then
      assertDoesNotThrow(
          () -> scheduleQueryService.checkIsSchedule(member, TEST_CATEGORY, LocalDate.now()));
    }

    @Test
    @DisplayName("있으면 예외를 던진다.")
    void checkIsScheduleAlreadyExistsSchedule() {
      // give
      when(scheduleRepository.existsByMemberAndCategoryAndDate(
              member, TEST_CATEGORY, LocalDate.now()))
          .thenReturn(true);

      // when
      ScheduleException scheduleException =
          assertThrows(
              ScheduleException.class,
              () -> scheduleQueryService.checkIsSchedule(member, TEST_CATEGORY, LocalDate.now()));

      // then
      assertEquals(scheduleException.getErrorCode(), GlobalErrorCode.ALREADY_EXISTS_SCHEDULE);
    }
  }
}
