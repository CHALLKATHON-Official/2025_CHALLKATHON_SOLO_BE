package com.example.solo.member.controller.swaggerDocs.constant;

public class LoginApiConstant {

  public static final String Login_Success =
      """
           "isSuccess": true,
           "code": "201",
           "message": "요청 성공 및 리소스 생성됨",
           "divideCode": "20101",
           "data": {
             "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzUwNjA0NTUxLCJleHAiOjE3NTA2MTQ1NTEsImlzcyI6Ik9NRiJ9.ETCN2arfOXAtYOFXWlv0A9-oNVR9easYJw6V-eStJ_M",
             "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzUwNjA0NTUxLCJleHAiOjE3NTE4MTQxNTEsImlzcyI6Ik9NRiJ9.lySiTQd6MpgzunqqdwZ5uT8YyGNN16sMGemPFoI2KqI
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
