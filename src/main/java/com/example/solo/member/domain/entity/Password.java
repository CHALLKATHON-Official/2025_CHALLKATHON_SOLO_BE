package com.example.solo.member.domain.entity;

import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.custom.MemberException;
import com.example.solo.member.domain.encrypt.PasswordEncryptor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Password {

  private static final String PASSWORD_REGEX =
      "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{}|;':\",./<>?~`]{8,16}$";

  @Column(name = "password", nullable = false, columnDefinition = "TEXT")
  private String encryptedPassword;

  public static Password encrypt(String plainPassword, PasswordEncryptor encryptor) {
    if (!isPasswordValid(plainPassword)) {
      throw new MemberException(GlobalErrorCode.NOT_VALID_PASSWORD);
    }
    return new Password(encryptor.encrypt(plainPassword));
  }

  private static Boolean isPasswordValid(String plainPassword) {
    return Pattern.matches(PASSWORD_REGEX, plainPassword);
  }

  public void isSamePassword(String plainPassword, PasswordEncryptor passwordEncoder) {
    if (!passwordEncoder.matches(plainPassword, encryptedPassword)) {
      throw new MemberException(GlobalErrorCode.NOT_VALID_PASSWORD);
    }
  }
}
