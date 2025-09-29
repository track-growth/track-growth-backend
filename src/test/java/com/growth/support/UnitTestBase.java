package com.growth.support;

import org.springframework.test.context.ActiveProfiles;

/**
 * 단위테스트용 베이스 클래스
 * - Mock을 사용한 빠른 단위테스트
 * - 스프링 컨텍스트 최소 로드
 */
@ActiveProfiles("test")
public abstract class UnitTestBase {

    // Mock 관련 공통 설정이나 유틸리티 메서드들을 여기에 추가
    protected void printTestInfo(String testName) {
        System.out.println("=== " + testName + " 테스트 시작 ===");
    }
}
