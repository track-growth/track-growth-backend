package com.growth.member.domain;

import com.growth.global.common.entity.BaseEntity;
import com.growth.member.dto.request.SignUpMemberRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID memberId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    public static Member from(SignUpMemberRequestDto requestDto, String encryptedPassword) {
        return Member.builder()
                .email(requestDto.email())
                .password(encryptedPassword)
                .nickname(requestDto.nickname())
                .build();
    }

    @Builder
    private Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

}
