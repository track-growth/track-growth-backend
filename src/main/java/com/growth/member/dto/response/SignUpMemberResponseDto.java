package com.growth.member.dto.response;

import com.growth.member.domain.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SignUpMemberResponseDto(

        String email,
        String nickname,
        LocalDateTime createdAt

) {
    public static SignUpMemberResponseDto from(Member member) {
        return SignUpMemberResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
