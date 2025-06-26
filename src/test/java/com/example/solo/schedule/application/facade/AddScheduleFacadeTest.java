package com.example.solo.schedule.application.facade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.example.solo.member.domain.encrypt.BCryptPasswordEncryptor;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.entity.Password;
import com.example.solo.member.domain.repository.MemberRepository;
import com.example.solo.schedule.domain.dto.request.AddScheduleRequestDto;
import com.example.solo.schedule.domain.repository.ScheduleRepository;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AddScheduleFacadeTest {

  @Autowired private AddScheduleFacade addScheduleFacade;
  @Autowired private MemberRepository memberRepository;
  @Autowired private ScheduleRepository scheduleRepository;

  private PasswordEncryptor encryptor;
  private Member member;

  private final String TEST_EMAIL = "test";
  private final String TEST_PASSWORD = "test1234";
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

    memberRepository.save(member);
  }

  @Test
  @DisplayName("일정을 생성한다.")
  void addSchedule() {
    // given
    AddScheduleRequestDto requestDto =
        new AddScheduleRequestDto(TEST_CATEGORY, TEST_HOUR, TEST_MINUTES);

    // when + then
    assertDoesNotThrow(() -> addScheduleFacade.addSchedule(member, requestDto));
  }
}
