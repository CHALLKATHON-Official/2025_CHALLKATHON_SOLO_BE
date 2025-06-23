package com.example.solo.member.domain.encrypt;

public interface PasswordEncryptor {

  String encrypt(String plainPassword);

  boolean matches(String plainPassword, String encryptedPassword);
}
