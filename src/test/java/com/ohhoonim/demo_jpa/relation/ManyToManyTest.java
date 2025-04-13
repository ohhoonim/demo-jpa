package com.ohhoonim.demo_jpa.relation;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.ohhoonim.demo_jpa.relation.repository.MtoMProductRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@Testcontainers
public class ManyToManyTest {

    @Container
    @ServiceConnection
    private final static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.2-alpine");

    @Autowired
    private MtoMProductRepository mtoMProductRepository;

    // @Autowired
    // private MtoMCategoryRepository mtoMCategoryEntityRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void manyToManyTest() {
        // category
        MtoMCategoryEntity category1 = new MtoMCategoryEntity();
        category1.setName("category1");

        MtoMCategoryEntity category2 = new MtoMCategoryEntity();
        category2.setName("category2");

        MtoMCategoryEntity category3 = new MtoMCategoryEntity();
        category3.setName("category3");

        // product
        MtoMProductEntity product1 = new MtoMProductEntity();
        product1.setName("test");
        product1.setPrice(new BigDecimal("100.00"));
        product1.setCreatedAt(LocalDateTime.now());
        product1.getCategories().add(category1);
        product1.getCategories().add(category3);

        MtoMProductEntity product2 = new MtoMProductEntity();
        product2.setName("test");
        product2.setPrice(new BigDecimal("100.00"));
        product2.setCreatedAt(LocalDateTime.now());
        product2.getCategories().add(category2);
        product2.getCategories().add(category3);

        var newProducts = List.of(product1, product2);

        mtoMProductRepository.saveAll(newProducts);

        clearEntity();

        System.out.println("==========================");
        var result = mtoMProductRepository.findAll();
        assertThat(result).hasSize(2);


    }
    private void clearEntity() {
        entityManager.flush(); // <- flush(), clear() 사용해야 제대로 테스트 가능
        entityManager.clear();
    }

}
