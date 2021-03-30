package com.jlb.spring.dbexo;

import com.jlb.spring.dbexo.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebFlux
@EnableTransactionManagement
public class DbexoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DbexoApplication.class, args);
	}

}
