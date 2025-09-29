package com.growth.support;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest(showSql = true,
        properties = {
                "spring.jpa.hibernate.ddl-auto = create-drop"
        })
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
