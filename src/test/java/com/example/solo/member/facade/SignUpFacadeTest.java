package com.example.solo.member.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.solo.member.application.facade.SignUpFacade;
import com.example.solo.member.domain.dto.request.SignUpRequestDto;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.repository.MemberRepository;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SignUpFacadeTest {

  @Autowired private SignUpFacade signUpFacade;

  private SignUpRequestDto requestDto;

  private final String TEST_EMAIL = "test";
  private final String TEST_PASSWORD = "test1234!@#$";
  private final String TEST_NICKNAME = "test";
  @Autowired private MemberRepository memberRepository;

  @BeforeEach
  void setUp() {
    requestDto = new SignUpRequestDto(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME);
  }

  @Test
  @DisplayName("회원가입을 한다.")
  void signUpSuccess() {
    // when
    signUpFacade.signUp(requestDto);

    // then
    Member member = memberRepository.findByEmail(TEST_EMAIL).get();
    assertEquals(TEST_EMAIL, member.getEmail());
  }
}
