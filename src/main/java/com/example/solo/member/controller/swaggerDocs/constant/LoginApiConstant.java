package com.example.solo.member.controller.swaggerDocs.constant;

public class LoginApiConstant {

  public static final String Login_Success =
      """
           {
                     "isSuccess": true,
                     "code": "string",
                     "message": "string",
                     "divideCode": "string",
                     "data": {
                       "accessToken": "string",
                       "refreshToken": "string"
                     }
                   }
      """;

  public static final String LOGIN_FAIL_NOT_VALID_PASSWORD =
      """
                    {
                      "isSuccess": false,
                      "code": "400",
                      "message": "비밀번호가 일치하지 않습니다.",
                      "divideCode": "40002"
                    }
              """;

  public static final String LOGIN_FAIL_NOT_FOUND_MEMBER =
      """
                    {
                            "isSuccess": false,
                            "code": "404",
                            "message": "회원을 찾을 수 없습니다.",
                            "divideCode": "40404"
                          }
              """;
}
