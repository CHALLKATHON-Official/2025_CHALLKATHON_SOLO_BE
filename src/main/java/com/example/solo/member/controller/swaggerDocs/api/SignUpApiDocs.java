package com.example.solo.member.controller.swaggerDocs.api;

import org.springframework.http.MediaType;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.member.controller.swaggerDocs.constant.SignUpApiConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class SignUpApiDocs {

  @Operation(summary = "회원가입", description = "회원가입을 합니다..")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "게시판 생성 성공",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples = @ExampleObject(value = SignUpApiConstant.SIGN_UP_SUCCESS))),
        @ApiResponse(
            responseCode = "40001",
            description = "이미 존재하는 이메일입니다.",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples =
                        @ExampleObject(
                            value = SignUpApiConstant.SIGN_UP_FAIL_ALREADY_EXIST_EMAIL))),
        @ApiResponse(
            responseCode = "40002",
            description = "이미 존재하는 닉네임입니다.",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples =
                        @ExampleObject(
                            value = SignUpApiConstant.SIGN_UP_FAIL_ALREADY_EXIST_NICKNAME)))
      })
  public void signUp() {}
}
