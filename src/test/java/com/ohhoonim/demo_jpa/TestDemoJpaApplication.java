package com.ohhoonim.demo_jpa;

import org.springframework.boot.SpringApplication;

public class TestDemoJpaApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoJpaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
