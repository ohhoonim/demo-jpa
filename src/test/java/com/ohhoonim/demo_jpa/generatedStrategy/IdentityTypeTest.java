package com.ohhoonim.demo_jpa.generatedStrategy;

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

import com.ohhoonim.demo_jpa.generatedStrategy.repository.ProductIdentityRepository;
import com.ohhoonim.demo_jpa.generatedStrategy.repository.ProductSequenceRepository;
import com.ohhoonim.demo_jpa.generatedStrategy.repository.ProductTableRepository;
import com.ohhoonim.demo_jpa.generatedStrategy.repository.PruductUuidRepository;

@DataJpaTest
@Testcontainers
public class IdentityTypeTest {

    @Container
    @ServiceConnection
    private final static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.2-alpine");

    @Test
    public void GeneratedValueStrategyTest() {
    }

    @Autowired
    private ProductIdentityRepository productIdentityRepository;

    @Test
    public void ProductIdentityRepositoryTest() {
        var product = new ProductIdentityEntity();
        product.setName("test");
        product.setPrice(new BigDecimal("100.00"));
        product.setCreatedAt(LocalDateTime.now());

        productIdentityRepository.save(product);

        List<ProductIdentityEntity> result = productIdentityRepository.findAll();
        assertThat(result).hasSize(1);
    }
    /////////////////////////////////////////
    // insert 
    // into
    //     product_identity
    //     (created_at, name, price) 
    // values
    //     (?, ?, ?)
    /////////////////////////////////////////
    // select
    //     pie1_0.id,
    //     pie1_0.created_at,
    //     pie1_0.name,
    //     pie1_0.price 
    // from
    //     product_identity pie1_0

    @Autowired
    private ProductSequenceRepository productSequenceRepository;

    @Test
    public void ProductSequenceRepositoryTest() {
        var product = new ProductSequenceEntity();
        product.setName("test");
        product.setPrice(new BigDecimal("100.00"));
        product.setCreatedAt(LocalDateTime.now());

        productSequenceRepository.save(product);

        List<ProductSequenceEntity> result = productSequenceRepository.findAll();

        assertThat(result).hasSize(1);
    }
    //////////////////////////////////////////// 
    // select
    // nextval('product_sequence_seq')
    ///////////////////////////////////////////// 
    // insert 
    // into
    //     product_sequence
    //     (created_at, name, price, id) 
    // values
    //     (?, ?, ?, ?)
    //////////////////////////////////////////// 
    //select
    //     pse1_0.id,
    //     pse1_0.created_at,
    //     pse1_0.name,
    //     pse1_0.price 
    // from
    //     product_sequence pse1_0 


    @Autowired
    private ProductTableRepository productTableRepository;

    @Test
    public void ProductTableRepositoryTest() {
        var product = new ProductTableEntity();
        product.setName("test");
        product.setPrice(new BigDecimal("100.00"));
        product.setCreatedAt(LocalDateTime.now());

        productTableRepository.save(product);

        List<ProductTableEntity> result = productTableRepository.findAll();

        assertThat(result).hasSize(1);
    }
    ///////////////////////////////////////////
    // select
    //     tbl.sequence_value 
    // from
    //     sequences tbl 
    // where
    //     tbl.sequence_name=? for update of tbl
    ///////////////////////////////////////////
    // update
    //     sequences 
    // set
    //     sequence_value=?  
    // where
    //     sequence_value=? 
    //     and sequence_name=?
    ///////////////////////////////////////////
    // insert 
    // into
    //     product_table
    //     (created_at, name, price, id) 
    // values
    //     (?, ?, ?, ?)
    ///////////////////////////////////////////
    // select
    //     pte1_0.id,
    //     pte1_0.created_at,
    //     pte1_0.name,
    //     pte1_0.price 
    // from
    //     product_table pte1_0


    @Autowired
    private PruductUuidRepository productUuidRepository;

    @Test
    public void ProductUuidRepositoryTest() {
        var product = new ProductUuidEntity();
        product.setName("test");
        product.setPrice(new BigDecimal("100.00"));
        product.setCreatedAt(LocalDateTime.now());

        productUuidRepository.save(product);

        List<ProductUuidEntity> result = productUuidRepository.findAll();

        assertThat(result).hasSize(1);
    }
    // /////////////////////////////////////////////////////
    // insert 
    // into
    //     product_uuid
    //     (created_at, name, price, id) 
    // values
    //     (?, ?, ?, ?)
    // /////////////////////////////////////////////////////
    // (1:TIMESTAMP) <- [2025-04-12T09:02:39.187241]
    // (2:VARCHAR) <- [test]
    // (3:NUMERIC) <- [100.00]
    // (4:UUID) <- [3756a888-9af9-4fe9-bce0-f06d1c14a87f]
    // /////////////////////////////////////////////////////
    // select
    //     pue1_0.id,
    //     pue1_0.created_at,
    //     pue1_0.name,
    //     pue1_0.price 
    // from
    //     product_uuid pue1_0
}
