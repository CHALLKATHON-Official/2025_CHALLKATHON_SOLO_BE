package com.example.solo.member.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
import com.example.solo.global.exception.custom.AuthException;
import com.example.solo.member.application.service.AuthQueryService;
import com.example.solo.member.domain.encrypt.BCryptPasswordEncryptor;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.entity.Password;
import com.example.solo.member.domain.repository.MemberRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AuthQueryServiceTest {

  @Mock private MemberRepository memberRepository;

  @InjectMocks private AuthQueryService authQueryService;

  private PasswordEncryptor encryptor;
  private Member member;

  private final String TEST_EMAIL = "test";
  private final String TEST_PASSWORD = "test1234!@#$";
  private final String TEST_NICKNAME = "test";

  @BeforeEach
  void setUp() {

    encryptor = new BCryptPasswordEncryptor(new BCryptPasswordEncoder());

    member =
        Member.builder()
            .email(TEST_EMAIL)
            .password(Password.encrypt(TEST_PASSWORD, encryptor))
            .build();
  }

  @Nested
  @DisplayName("중복된 이메일이 존재하는지 조회한다.")
  class isSameEmail {

    @Test
    @DisplayName("존재하지 않으면 진행한다.")
    void isSameEmailSuccess() {
      // give
      when(memberRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());

      // when + then
      assertDoesNotThrow(() -> authQueryService.isSameEmail(TEST_EMAIL));
    }

    @Test
    @DisplayName("존재하면 예외를 던진다.")
    void isSameEmailFailAlreadyExistsEmail() {
      // give
      when(memberRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(member));

      // when
      AuthException exception =
          assertThrows(AuthException.class, () -> authQueryService.isSameEmail(TEST_EMAIL));

      // then
      assertEquals(GlobalErrorCode.ALREADY_EXISTS_EMAIL, exception.getErrorCode());
    }
  }

  @Nested
  @DisplayName("중복된 닉네임이 존재하는지 조회한다.")
  class isSameNickname {

    @Test
    @DisplayName("존재하지 않으면 진행한다.")
    void isSameNicknameSuccess() {
      // give
      when(memberRepository.findByNickname(TEST_NICKNAME)).thenReturn(Optional.empty());

      // when + then
      assertDoesNotThrow(() -> authQueryService.isSameNickname(TEST_NICKNAME));
    }

    @Test
    @DisplayName("존재하면 예외를 던진다.")
    void isSameNicknameFailAlreadyExistsNickname() {
      // give
      when(memberRepository.findByNickname(TEST_NICKNAME)).thenReturn(Optional.of(member));

      // when
      AuthException exception =
          assertThrows(AuthException.class, () -> authQueryService.isSameNickname(TEST_NICKNAME));

      // then
      assertEquals(GlobalErrorCode.ALREADY_EXISTS_NICKNAME, exception.getErrorCode());
    }
  }
}
