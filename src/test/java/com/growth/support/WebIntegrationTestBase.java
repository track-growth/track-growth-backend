package com.growth.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.test.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;


/**
 * 웹 계층 테스트용 베이스 클래스
 * - MockMvc나 WebTestClient를 사용한 웹 계층 테스트
 * - 실제 HTTP 요청/응답 테스트
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public abstract class WebIntegrationTestBase {

    @Autowired
    protected TestRestTemplate restTemplate;

    // 공통 헤더 생성 유틸리티
    protected HttpHeaders createJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    // 인증 헤더 생성 유틸리티 (JWT 토큰 등)
    protected HttpHeaders createAuthHeaders(String token) {
        HttpHeaders headers = createJsonHeaders();
        headers.setBearerAuth(token);
        return headers;
    }
}
