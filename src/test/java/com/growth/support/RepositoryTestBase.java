package com.growth.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

/**
 * Repository 테스트용 베이스 클래스
 * - @DataJpaTest를 사용한 JPA 레이어만 테스트
 */
@DataJpaTest
@ActiveProfiles("test")
public abstract class RepositoryTestBase {

    @Autowired
    protected TestEntityManager testEntityManager;

    protected <T> T persistAndFlush(T entity) {
        T saved = testEntityManager.persistAndFlush(entity);
        testEntityManager.clear();
        return saved;
    }
}
