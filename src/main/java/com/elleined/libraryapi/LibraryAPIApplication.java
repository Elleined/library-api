package com.elleined.libraryapi;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LibraryAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAPIApplication.class, args);
	}

	@Bean
	public Faker faker() {
		return new Faker();
	}
}
