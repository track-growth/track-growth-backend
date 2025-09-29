package com.growth.support;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * 통합테스트용 베이스 클래스
 * - H2 데이터베이스를 사용한 실제 통합테스트 -> 후에 테스트 컨테이너로 변경
 * - 전체 스프링 컨텍스트 로드
 * - 트랜잭션 롤백으로 테스트 간 데이터 격리
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public abstract class IntegrationTestBase {

    @PersistenceContext
    protected EntityManager entityManager;

    @AfterEach
    void cleanUp() {
        entityManager.flush();
        entityManager.clear();
    }
}