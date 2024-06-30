package com.elleined.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class LibraryAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAPIApplication.class, args);
	}


}
