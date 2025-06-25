package com.example.solo.global.exception.custom;

import com.example.solo.global.exception.GlobalErrorCode;

public class FriendException extends RuntimeException {

  private final GlobalErrorCode errorCode;

  public FriendException(GlobalErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public GlobalErrorCode getErrorCode() {
    return errorCode;
  }
}
