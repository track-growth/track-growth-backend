package com.growth.member.domain;

import com.growth.member.dto.request.SignUpMemberRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Member 엔티티 테스트")
class MemberTest {

    @Test
    @DisplayName("Builder로 Member 객체를 생성할 수 있다")
    void createMember_WithBuilder() {
        // when
        Member member = Member.builder()
                .email("test@example.com")
                .password("encryptedPassword")
                .nickname("testuser")
                .build();

        // then
        assertThat(member.getEmail()).isEqualTo("test@example.com");
        assertThat(member.getPassword()).isEqualTo("encryptedPassword");
        assertThat(member.getNickname()).isEqualTo("testuser");
    }

    @Test
    @DisplayName("from 메서드로 DTO를 Member로 변환할 수 있다")
    void createMember_WithFromMethod() {
        // given
        SignUpMemberRequestDto requestDto = new SignUpMemberRequestDto(
                "test@example.com",
                "rawPassword",
                "testuser"
        );
        String encryptedPassword = "encryptedPassword";

        // when
        Member member = Member.from(requestDto, encryptedPassword);

        // then
        assertThat(member.getEmail()).isEqualTo(requestDto.email());
        assertThat(member.getPassword()).isEqualTo(encryptedPassword);
        assertThat(member.getNickname()).isEqualTo(requestDto.nickname());
    }
}