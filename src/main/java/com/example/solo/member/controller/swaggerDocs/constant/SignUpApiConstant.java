package com.example.solo.member.controller.swaggerDocs.constant;

public class SignUpApiConstant {

  public static final String SIGN_UP_SUCCESS =
      """
      {
        "isSuccess": true,
        "code": "201",
        "message": "요청 성공 및 리소스 생성됨",
        "divideCode": "20101"
      }
      """;

  public static final String SIGN_UP_FAIL_ALREADY_EXIST_EMAIL =
      """
        {
          "isSuccess": false,
          "code": "400",
          "message": "이미 존재하는 이메일입니다.",
          "divideCode": "4001"
        }
        """;

  public static final String SIGN_UP_FAIL_ALREADY_EXIST_NICKNAME =
      """
        {
          "isSuccess": false,
          "code": "400",
          "message": "이미 존재하는 이메일입니다.",
          "divideCode": "4001"
        }
        """;
}
