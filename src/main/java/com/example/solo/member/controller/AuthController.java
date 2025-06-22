package com.example.solo.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.solo.global.annotation.SwaggerDocs;
import com.example.solo.global.exception.BaseResponse;
import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.member.application.facade.SignUpFacade;
import com.example.solo.member.controller.swaggerDocs.api.SignUpApiDocs;
import com.example.solo.member.domain.dto.request.SignUpRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final SignUpFacade signUpFacade;

  @SwaggerDocs(SignUpApiDocs.class)
  @PostMapping
  public ResponseEntity<BaseResponse<Void>> signUp(@RequestBody SignUpRequestDto requestDto) {
    signUpFacade.signUp(requestDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body(BaseResponse.onSuccess(GlobalErrorCode.CREATED, null));
  }
}
