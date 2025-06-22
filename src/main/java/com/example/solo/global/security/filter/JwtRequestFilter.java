package com.example.solo.global.security.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.GlobalException;
import com.example.solo.global.exception.custom.TokenException;
import com.example.solo.global.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailsService userDetailsService;
  private final AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader != null) {
      String token = authorizationHeader;
      if (jwtTokenProvider.isTokenValid(token)) {
        Long userId = jwtTokenProvider.getMemberIdFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId.toString());
        if (userDetails != null) {
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, "", userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
          throw new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER);
        }
      } else {
        throw new TokenException(GlobalErrorCode.INVALID_TOKEN);
      }
    }
    filterChain.doFilter(request, response);
  }
}
