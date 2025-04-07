package com.ohhoonim.demo_jpa.generatedStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Data
@TableGenerator(
        name = "product_table_generator",
        table = "sequences",
        pkColumnName = "sequence_name",
        valueColumnName = "sequence_value",
        pkColumnValue = "product_table_sequence",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "product_table")
@Entity
public class ProductTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
            generator = "product_table_generator")
    private Long id;

    private String name;

    private BigDecimal price;

    private LocalDateTime createdAt;
}

/*
create table product_table (
    price numeric(38,2),
    created_at timestamp(6),
    id bigint not null,
    name varchar(255),
    primary key (id)
) 
create table sequences (
    sequence_value bigint,
    sequence_name varchar(255) not null,
    primary key (sequence_name)
)

insert into sequences(sequence_name, sequence_value) values ('product_table_sequence',1)             
 */