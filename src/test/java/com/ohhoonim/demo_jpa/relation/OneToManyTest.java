package com.ohhoonim.demo_jpa.relation;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.ohhoonim.demo_jpa.relation.repository.CategoryManyRepository;
import com.ohhoonim.demo_jpa.relation.repository.ProductOneRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@Testcontainers
public class OneToManyTest {

    @Container
    @ServiceConnection
    private final static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.2-alpine");

    @Autowired
    private ProductOneRepository productOneRepository;

    @Autowired
    private CategoryManyRepository categoryManyRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void oneToManyTest_01() {
        // product 저장
        var product = new ProductOneEntity();
        product.setName("test");
        product.setPrice(new BigDecimal("100.00"));
        product.setCreatedAt(LocalDateTime.now());

        productOneRepository.save(product); // <- 이 시점에서 product의 id가 생성된다. 
        // product 는 immutable 하지 않다.(명심하자) 
        clearEntity();

        // category 저장 
        // jpa는 Entity에 default 생성자를 요구한다. entityManager.claer() 시점에서
        var categories = List.of(
                new CategoryManyEntity("category1", product),
                new CategoryManyEntity("category2", product),
                new CategoryManyEntity("category3", product),
                new CategoryManyEntity("category4", product));

        categoryManyRepository.saveAll(categories);
        clearEntity();

        System.out.println("==========================");
        Optional<ProductOneEntity> result = productOneRepository.findById(product.getId());

        System.out.println("==========================");
        // assertThat(result.get().getCategories()).hasSize(2);
        var categoriesResult = result.get().getCategories();
        System.out.println("==========================");
        assertThat(categoriesResult.get(0).getName()).isEqualTo("category1");
        System.out.println("==========================");
        assertThat(categoriesResult.get(1).getName()).isEqualTo("category2");
        System.out.println("==========================");
        assertThat(categoriesResult.get(2).getName()).isEqualTo("category3");
        System.out.println("==========================");
        assertThat(categoriesResult.get(3).getName()).isEqualTo("category4");

        // 테스트 해봤으면 fetchType을 lazy로 바꿔보자 
    }
    // /////////////////////////////////////////////////////
    // insert 
    // into
    //     product_one2many
    //     (created_at, name, price) 
    // values
    //     (?, ?, ?)
    // /////////////////////////////////////////////////////
    // insert 
    // into
    //     category_one2many
    //     (name, product_id) 
    // values
    //     (?, ?)
    // /////////////////////////////////////////////////////
    // 첫번째 
    // (1:VARCHAR) <- [category1] 
    // (2:BIGINT) <- [1] 
    // 두번째 
    // (1:VARCHAR) <- [category2]
    // (2:BIGINT) <- [1]
    // /////////////////////////////////////////////////////
    //select
    //     poe1_0.id,
    //     poe1_0.created_at,
    //     poe1_0.name,
    //     poe1_0.price,
    //     c1_0.product_id,
    //     c1_0.id,
    //     c1_0.name 
    // from
    //     product_one2many poe1_0 
    // left join
    //     category_one2many c1_0 
    //         on poe1_0.id=c1_0.product_id 
    // where
    //     poe1_0.id=?
    // /////////////////////////////////////////////
    // select
    //     cme1_0.id,
    //     cme1_0.name,
    //     cme1_0.product_id 
    // from
    //     category_one2many cme1_0 
    // where
    //     cme1_0.product_id=?

    private void clearEntity() {
        entityManager.flush(); // <- flush(), clear() 사용해야 제대로 테스트 가능
        entityManager.clear();
    }

    // 아래 예제는 CascadeType.ALL 설정 필요
    // categoryRepository를 별도로 호출하지 않고 product에 담아서 저장 
    @Test
    public void oneToManyTest_02() {
        // product 저장
        var product = new ProductOneEntity();
        product.setName("test");
        product.setPrice(new BigDecimal("100.00"));
        product.setCreatedAt(LocalDateTime.now());

        var categories = List.of(
                new CategoryManyEntity("category1", product),
                new CategoryManyEntity("category2", product));

        // category는 product에 담아서 한번에 저장 
        // (주의) product 정보가 빠지면 안됨 
        // 기존에 category가 있을 경우 어떻게 될 것인지에 대한 문제는 entity 상태전략을
        // 이해하면 헷갈릴 이유가 없을 것 같지만, 삭제는 어떻게 되는지 추가 테스트 필요 
        product.getCategories().addAll(categories);

        productOneRepository.save(product);

        clearEntity();

        System.out.println("==========================");
        Optional<ProductOneEntity> result = productOneRepository.findById(product.getId());

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getCategories()).hasSize(2);
    }
}
