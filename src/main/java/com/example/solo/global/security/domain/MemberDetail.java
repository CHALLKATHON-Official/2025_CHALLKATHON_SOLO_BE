package com.example.solo.global.security.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.solo.member.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberDetail implements UserDetails {

  private final Member member;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<String> roles = List.of("ROLE_USER");

    return roles.stream().map(SimpleGrantedAuthority::new).toList();
  }

  @Override
  public String getPassword() {
    return "";
  }

  @Override
  public String getUsername() {
    return member.getId().toString();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Member getMember() {
    return member;
  }
}
