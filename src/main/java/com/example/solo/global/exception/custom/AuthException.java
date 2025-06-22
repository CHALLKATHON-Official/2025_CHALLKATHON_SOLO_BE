package com.example.solo.global.exception.custom;

import com.example.solo.global.exception.GlobalErrorCode;

public class AuthException extends RuntimeException {

  private final GlobalErrorCode errorCode;

  public AuthException(GlobalErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public GlobalErrorCode getErrorCode() {
    return errorCode;
  }
}
