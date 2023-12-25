package com.example.TravelProject;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TravelProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelProjectApplication.class, args);
	}


	@Bean
	JPAQueryFactory queryFactory(EntityManager em){
		return new JPAQueryFactory(em);
	}
}
