package com.example.solo.member.domain.encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BCryptPasswordEncryptor implements PasswordEncryptor {

  private final BCryptPasswordEncoder encoder;

  @Override
  public String encrypt(String plainPassword) {
    return encoder.encode(plainPassword);
  }

  @Override
  public boolean matches(String plainPassword, String encryptedPassword) {
    return encoder.matches(plainPassword, encryptedPassword);
  }
}
