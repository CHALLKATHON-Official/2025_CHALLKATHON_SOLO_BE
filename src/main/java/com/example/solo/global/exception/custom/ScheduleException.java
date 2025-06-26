package com.example.solo.global.exception.custom;

import com.example.solo.global.exception.GlobalErrorCode;

public class ScheduleException extends RuntimeException {

  private final GlobalErrorCode errorCode;

  public ScheduleException(GlobalErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public GlobalErrorCode getErrorCode() {
    return errorCode;
  }
}
