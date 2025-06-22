package com.example.solo.global.exception.custom;

import com.example.solo.global.exception.GlobalErrorCode;

public class MemberException extends RuntimeException {

  private final GlobalErrorCode errorCode;

  public MemberException(GlobalErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public GlobalErrorCode getErrorCode() {
    return errorCode;
  }
}
