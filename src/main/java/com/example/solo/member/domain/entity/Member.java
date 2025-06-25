package com.example.solo.member.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  private String email;

  @Embedded private Password password;

  private String nickname;

  @OneToMany(mappedBy = "fromMember", cascade = CascadeType.ALL)
  private List<Friend> fromFriends = new ArrayList<>();

  @OneToMany(mappedBy = "toMember", cascade = CascadeType.ALL)
  private List<Friend> toFriends = new ArrayList<>();
}
