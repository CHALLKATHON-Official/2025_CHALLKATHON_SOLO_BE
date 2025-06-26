package com.example.solo.member.application.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.example.solo.member.domain.dto.request.LoginRequestDto;
import com.example.solo.member.domain.dto.response.AuthTokenResponse;
import com.example.solo.member.domain.encrypt.BCryptPasswordEncryptor;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.entity.Password;
import com.example.solo.member.domain.repository.MemberRepository;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class LoginFacadeTest {

  @Autowired private LoginFacade loginFacade;

  private LoginRequestDto requestDto;

  private final String TEST_EMAIL = "test";
  private final String TEST_PASSWORD = "test1234";
  private final String TEST_NICKNAME = "test";

  private Member member;

  @Autowired private MemberRepository memberRepository;
  @Autowired private PasswordEncryptor encryptor;

  @BeforeEach
  void setUp() {
    requestDto = new LoginRequestDto(TEST_EMAIL, TEST_PASSWORD);

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
  @DisplayName("로그인을 한다.")
  void loginSuccess() {
    // when
    AuthTokenResponse response = loginFacade.login(requestDto);

    // then
    assertNotNull(response.accessToken());
    assertNotNull(response.refreshToken());
  }
}
