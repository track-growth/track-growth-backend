package com.growth.member.controller;

import com.growth.support.WebIntegrationTestBase;
import com.growth.member.dto.request.SignUpMemberRequestDto;
import com.growth.member.repository.MemberJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MemberCommandController WebTestClient 테스트")
class MemberCommandControllerWebTest extends WebIntegrationTestBase {

    @Autowired
    private MemberJpaRepository memberRepository;

    @Test
    @DisplayName("회원가입을 할 수 있다")
    void signUp_Success() {
        // given
        SignUpMemberRequestDto requestDto = new SignUpMemberRequestDto(
                "test@example.com",
                "password123",
                "testuser"
        );

        // when & then
        webTestClient.post()
                .uri("/api/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.statusCode").isEqualTo(201)
                .jsonPath("$.message").isEqualTo("회원가입이 완료되었습니다")
                .jsonPath("$.data.email").isEqualTo("test@example.com")
                .jsonPath("$.data.nickname").isEqualTo("testuser")
                .jsonPath("$.data.createdAt").exists();

        // 데이터베이스 저장 확인
        assertThat(memberRepository.existsByEmail("test@example.com")).isTrue();
    }

    @Test
    @DisplayName("중복 이메일로 회원가입 시 400 에러가 발생한다")
    void signUp_DuplicateEmail_ReturnsBadRequest() {
        // given - 기존 회원 생성
        SignUpMemberRequestDto existingMember = new SignUpMemberRequestDto(
                "duplicate@example.com",
                "password123",
                "existing"
        );

        // 첫 번째 회원 생성
        webTestClient.post()
                .uri("/api/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(existingMember)
                .exchange()
                .expectStatus().isOk();

        // 중복 이메일로 가입 시도
        SignUpMemberRequestDto duplicateRequest = new SignUpMemberRequestDto(
                "duplicate@example.com",
                "newpassword",
                "newuser"
        );

        // when & then - ProblemDetail 응답 검증
        webTestClient.post()
                .uri("/api/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(duplicateRequest)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("잘못된 요청입니다")
                .jsonPath("$.detail").isEqualTo("이미 존재하는 이메일입니다")
                .jsonPath("$.status").isEqualTo(400);
    }

    @Test
    @DisplayName("잘못된 요청 데이터로 400 에러가 발생한다")
    void signUp_InvalidRequest_ReturnsBadRequest() {
        // given - 빈 이메일
        SignUpMemberRequestDto invalidRequest = new SignUpMemberRequestDto(
                "",
                "password123",
                "testuser"
        );

        // when & then - ProblemDetail 응답 검증
        webTestClient.post()
                .uri("/api/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidRequest)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("잘못된 요청입니다")
                .jsonPath("$.detail").isEqualTo("입력값 검증에 실패했습니다")
                .jsonPath("$.status").isEqualTo(400)
                .jsonPath("$.errors").exists();
    }

    @Test
    @DisplayName("JSON 형식이 잘못된 경우 400 에러가 발생한다")
    void signUp_InvalidJson_ReturnsBadRequest() {
        // given
        String invalidJson = "{ invalid json }";

        // when & then - ProblemDetail 응답 검증
        webTestClient.post()
                .uri("/api/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidJson)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("잘못된 요청입니다")
                .jsonPath("$.detail").isEqualTo("잘못된 JSON 형식입니다")
                .jsonPath("$.status").isEqualTo(400);
    }

    @Test
    @DisplayName("응답 데이터 타입 검증")
    void signUp_ResponseTypeValidation() {
        // given
        SignUpMemberRequestDto requestDto = new SignUpMemberRequestDto(
                "typetest@example.com",
                "password123",
                "typetest"
        );

        // when & then
        webTestClient.post()
                .uri("/api/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.statusCode").isNumber()
                .jsonPath("$.message").isNotEmpty()
                .jsonPath("$.data").exists()
                .jsonPath("$.data.email").isNotEmpty()
                .jsonPath("$.data.nickname").isNotEmpty()
                .jsonPath("$.data.createdAt").isNotEmpty();
    }
}