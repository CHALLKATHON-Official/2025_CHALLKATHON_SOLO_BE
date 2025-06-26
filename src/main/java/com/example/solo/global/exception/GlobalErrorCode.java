package com.example.solo.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode {

  // 200
  OK(HttpStatus.OK, "20001", "요청 성공"),
  UPDATED(HttpStatus.OK, "20002", "요청 성공 및 리소스 수정됨"),

  // 201
  CREATED(HttpStatus.CREATED, "20101", "요청 성공 및 리소스 생성됨"),

  // 202
  ACCEPTED(HttpStatus.ACCEPTED, "20201", "요청 수락됨, 처리 진행 중"),

  // 204
  DELETED(HttpStatus.NO_CONTENT, "20401", "요청 성공 및 리소스 삭제됨"),

  // 205
  RESET_CONTENT(HttpStatus.RESET_CONTENT, "20501", "요청 성공 및 콘텐츠 초기화됨"),

  // 206
  PARTIAL_CONTENT(HttpStatus.PARTIAL_CONTENT, "20601", "요청 성공 및 부분 콘텐츠 반환됨"),

  // 400 BAD_REQUEST
  NOT_VALID_PASSWORD(HttpStatus.BAD_REQUEST, "40001", "비밀번호가 일치하지 않습니다."),
  ALREADY_EXISTS_EMAIL(HttpStatus.BAD_REQUEST, "40002", "이미 존재하는 이메일입니다."),
  ALREADY_EXISTS_NICKNAME(HttpStatus.BAD_REQUEST, "40003", "이미 존재하는 닉네임입니다."),
  ALREADY_EXISTS_SCHEDULE(HttpStatus.BAD_REQUEST, "40004", "이미 일정이 존재합니다."),

  // 401 UNAUTHORIZED
  TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "40101", "토큰의 유효기간이 지났습니다."),
  INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "40102", "잘못된 토큰입니다."),
  AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "40103", "인증이 필요합니다."),

  // 403 FORBIDDEN
  ACCESS_DENIED(HttpStatus.FORBIDDEN, "40301", "권한이 없습니다."),

  // 404 NOT_FOUND
  NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "40404", "회원을 찾을 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String divideCode;
  private final String message;
}
