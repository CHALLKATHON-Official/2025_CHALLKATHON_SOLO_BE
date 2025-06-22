package com.example.solo.global.security.constant;

public final class AllowUrl {

  public static final String[] PERMIT_ALL_URLS = {
    "/h2-console/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/v3/api-docs/**",
    "/actuator/**",
    "/api/v1/auth/**"
  };
}
