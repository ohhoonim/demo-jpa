package com.ohhoonim.demo_jpa.generatedStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@SequenceGenerator(name = "product_sequence_generator", 
        sequenceName = "product_sequence_seq", 
        initialValue = 1, allocationSize = 1)
@Table(name = "product_sequence")
@Entity
public class ProductSequenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
            generator = "product_sequence_generator")
    private Long id;

    private String name;

    private BigDecimal price;

    private LocalDateTime createdAt;
}

/*
 drop table if exists product_sequence cascade 
 drop sequence if exists product_sequence_seq

 create sequence product_sequence_seq start with 1 increment by 1
 
 create table product_sequence (
        price numeric(38,2),
        created_at timestamp(6),
        id bigint not null,
        name varchar(255),
        primary key (id)
    )

 */