package com.example.solo.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.example.solo.member.application.service.AuthCommandService;
import com.example.solo.member.domain.dto.request.SignUpRequestDto;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;
import com.example.solo.member.domain.entity.Member;
import com.example.solo.member.domain.repository.MemberRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AuthCommandServiceTest {

  @Mock private MemberRepository memberRepository;
  @Mock private PasswordEncryptor encryptor;

  @InjectMocks private AuthCommandService authCommandService;

  private final String TEST_EMAIL = "test";
  private final String TEST_PASSWORD = "test1234!@#$";
  private final String TEST_NICKNAME = "test";

  @Test
  @DisplayName("회원을 생성한다.")
  void createMember() {
    // give
    SignUpRequestDto requestDto = new SignUpRequestDto(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME);

    // when
    authCommandService.createMember(requestDto);

    // then
    ArgumentCaptor<Member> memberCaptor = ArgumentCaptor.forClass(Member.class);
    verify(memberRepository).save(memberCaptor.capture());
    assertEquals(memberCaptor.getValue().getEmail(), TEST_EMAIL);
  }
}
