package com.example.solo.member.controller.swaggerDocs.api;

import org.springframework.http.MediaType;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.member.controller.swaggerDocs.constant.LoginApiConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class LoginApiDocs {

  @Operation(summary = "로그인", description = "로그인을 합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "로그인 성공",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples = @ExampleObject(value = LoginApiConstant.Login_Success))),
        @ApiResponse(
            responseCode = "40001",
            description = "비밀번호가 일치하지 않습니다.",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples =
                        @ExampleObject(value = LoginApiConstant.LOGIN_FAIL_NOT_VALID_PASSWORD))),
        @ApiResponse(
            responseCode = "40404",
            description = "회원을 찾을 수 없습니다.",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples =
                        @ExampleObject(value = LoginApiConstant.LOGIN_FAIL_NOT_FOUND_MEMBER)))
      })
  public void login() {}
}
