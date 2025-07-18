package com.example.solo.global.security.jwt;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.solo.global.exception.GlobalErrorCode;
import com.example.solo.global.exception.GlobalException;
import com.example.solo.global.exception.custom.TokenException;
import com.example.solo.member.domain.repository.MemberRepository;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

  private final Key key;
  private final long accessTokenValidityMilliseconds;
  private final long refreshTokenValidityMilliseconds;
  private final MemberRepository memberRepository;

  public JwtTokenProvider(
      @Value("${jwt.secret}") String secretKey,
      @Value("${jwt.access-token-validity}") final long accessTokenValidityMilliseconds,
      @Value("${jwt.refresh-token-validity}") final long refreshTokenValidityMilliseconds,
      MemberRepository memberRepository) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.accessTokenValidityMilliseconds = accessTokenValidityMilliseconds;
    this.refreshTokenValidityMilliseconds = refreshTokenValidityMilliseconds;
    this.memberRepository = memberRepository;
  }

  private String generateToken(Long userId, long validityMilliseconds) {
    Claims claims = Jwts.claims();

    ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTime tokenValidity = now.plusSeconds(validityMilliseconds / 1000);

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(userId.toString())
        .setIssuedAt(Date.from(now.toInstant()))
        .setExpiration(Date.from(tokenValidity.toInstant()))
        .setIssuer("OMF")
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public String generateAccessToken(Long userId) {
    String accessToken = generateToken(userId, accessTokenValidityMilliseconds);
    if (isTokenValid(accessToken)) {
      setAuthentication(userId);
    }
    return accessToken;
  }

  private void setAuthentication(Long userId) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userId, null, null);
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }

  public String generateRefreshToken(Long userId) {

    memberRepository
        .findById(userId)
        .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    return generateToken(userId, refreshTokenValidityMilliseconds);
  }

  public String extractSubject(String accessToken) {
    Claims claims = parseClaims(accessToken);
    return claims.getSubject();
  }

  public Long getMemberIdFromToken(String accessToken) {
    Claims claims = parseClaims(accessToken);
    return Long.valueOf(claims.getSubject());
  }

  public Claims parseClaims(String accessToken) {
    try {
      return getClaims(accessToken).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  private Jws<Claims> getClaims(String token) {
    if (token.startsWith("Bearer")) {
      token = token.substring(6).trim();
    }
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }

  public boolean isTokenValid(String token) {
    try {
      Jws<Claims> claims = getClaims(token);
      Date expiredDate = claims.getBody().getExpiration();
      return expiredDate.after(new Date());
    } catch (ExpiredJwtException e) {
      throw new TokenException(GlobalErrorCode.TOKEN_EXPIRED);
    } catch (SecurityException
        | MalformedJwtException
        | UnsupportedJwtException
        | IllegalArgumentException e) {
      throw new TokenException(GlobalErrorCode.INVALID_TOKEN);
    }
  }

  public Long parseRefreshToken(String token) {
    if (isTokenValid(token)) {
      Claims claims = getClaims(token).getBody();
      return Long.parseLong(claims.getSubject());
    }
    throw new TokenException(GlobalErrorCode.INVALID_TOKEN);
  }
}
