package com.example.solo.member.domain.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Friend {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "friend_id")
  private Long id;

  @Setter @Builder.Default private Boolean isFriend = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "from_member_id")
  private Member fromMember;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "to_member_id")
  private Member toMember;
}
