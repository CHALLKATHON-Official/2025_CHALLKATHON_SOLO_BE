package com.example.solo.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.solo.global.annotation.SwaggerDocs;
import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.member.application.facade.LoginFacade;
import com.example.solo.member.application.facade.SignUpFacade;
import com.example.solo.member.controller.swaggerDocs.api.LoginApiDocs;
import com.example.solo.member.controller.swaggerDocs.api.SignUpApiDocs;
import com.example.solo.member.domain.dto.request.LoginRequestDto;
import com.example.solo.member.domain.dto.request.SignUpRequestDto;
import com.example.solo.member.domain.dto.response.AuthTokenResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final SignUpFacade signUpFacade;
  private final LoginFacade loginFacade;

  @SwaggerDocs(SignUpApiDocs.class)
  @PostMapping("/join")
  public ResponseEntity<BaseResponse<Void>> signUp(@RequestBody SignUpRequestDto requestDto) {
    signUpFacade.signUp(requestDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body(BaseResponse.onSuccess(GlobalErrorCode.CREATED, null));
  }

  @SwaggerDocs(LoginApiDocs.class)
  @PostMapping("/login")
  public ResponseEntity<BaseResponse<AuthTokenResponse>> login(
      @RequestBody LoginRequestDto requestDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(BaseResponse.onSuccess(GlobalErrorCode.CREATED, loginFacade.login(requestDto)));
  }
}
