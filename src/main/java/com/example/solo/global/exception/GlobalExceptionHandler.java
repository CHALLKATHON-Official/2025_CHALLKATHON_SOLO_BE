package com.example.solo.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.solo.global.exception.custom.AuthException;
import com.example.solo.global.exception.custom.MemberException;
import com.example.solo.global.exception.custom.ScheduleException;
import com.example.solo.global.exception.custom.TokenException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {GlobalException.class})
  protected ResponseEntity<BaseResponse<?>> handleCustomException(GlobalException e) {
    log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(BaseResponse.onFailure(e.getErrorCode(), null));
  }

  @ExceptionHandler(value = {MemberException.class})
  protected ResponseEntity<BaseResponse<?>> handleCustomException(MemberException e) {
    log.error("handleCustomException throw MemberException : {}", e.getErrorCode());
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(BaseResponse.onFailure(e.getErrorCode(), null));
  }

  @ExceptionHandler(value = {TokenException.class})
  protected ResponseEntity<BaseResponse<?>> handleCustomException(TokenException e) {
    log.error("handleCustomException throw TokenException : {}", e.getErrorCode());
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(BaseResponse.onFailure(e.getErrorCode(), null));
  }

  @ExceptionHandler(value = {AuthException.class})
  protected ResponseEntity<BaseResponse<?>> handleCustomException(AuthException e) {
    log.error("handleCustomException throw AuthException : {}", e.getErrorCode());
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(BaseResponse.onFailure(e.getErrorCode(), null));
  }

  @ExceptionHandler(value = {ScheduleException.class})
  protected ResponseEntity<BaseResponse<?>> handleCustomException(ScheduleException e) {
    log.error("handleCustomException throw ScheduleException : {}", e.getErrorCode());
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(BaseResponse.onFailure(e.getErrorCode(), null));
  }
}
