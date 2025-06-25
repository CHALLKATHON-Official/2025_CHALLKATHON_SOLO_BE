package com.example.solo.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.solo.member.domain.entity.Friend;
import com.example.solo.member.domain.entity.Member;

public interface FriendRepository extends JpaRepository<Friend, Long> {

  Boolean existsByFromMemberAndToMember(Member toMember, Member fromMember);
}
