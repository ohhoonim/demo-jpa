package com.ohhoonim.demo_jpa.generatedStrategy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
public class IdentityTypeTest {
    
    @Container
    @ServiceConnection
    private final static PostgreSQLContainer<?> postgreSQLContainer = 
            new PostgreSQLContainer<>("postgres:17.2-alpine");

    
    
    @Test
    public void GeneratedValueStrategyTest() {
    }
}
