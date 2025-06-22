package com.example.solo.global.exception.custom;

import com.example.solo.global.exception.GlobalErrorCode;

public class TokenException extends RuntimeException {

  private final GlobalErrorCode errorCode;

  public TokenException(GlobalErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public GlobalErrorCode getErrorCode() {
    return errorCode;
  }
}
