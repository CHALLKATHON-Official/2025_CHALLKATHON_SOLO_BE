package com.example.solo.schedule.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.example.solo.member.domain.encrypt.BCryptPasswordEncryptor;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.entity.Password;
import com.example.solo.schedule.domain.dto.request.AddScheduleRequestDto;
import com.example.solo.schedule.domain.entity.Schedule;
import com.example.solo.schedule.domain.repository.ScheduleRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ScheduleCommandServiceTest {

  @Mock private ScheduleRepository scheduleRepository;

  @InjectMocks private ScheduleCommandService scheduleCommandService;

  private PasswordEncryptor encryptor;
  private Member member;

  private final String TEST_EMAIL = "test";
  private final String TEST_PASSWORD = "test1234!@#$";
  private final String TEST_NICKNAME = "test";
  private final String TEST_CATEGORY = "test";
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
  @DisplayName("일정을 생성한다.")
  void createScheduleSuccess() {
    // give
    AddScheduleRequestDto requestDto =
        new AddScheduleRequestDto(TEST_CATEGORY, TEST_HOUR, TEST_MINUTES);

    // when
    scheduleCommandService.createSchedule(member, requestDto);

    // then
    ArgumentCaptor<Schedule> scheduleCaptor = ArgumentCaptor.forClass(Schedule.class);
    verify(scheduleRepository).save(scheduleCaptor.capture());
    assertEquals(scheduleCaptor.getValue().getCategory(), TEST_CATEGORY);
  }
}
