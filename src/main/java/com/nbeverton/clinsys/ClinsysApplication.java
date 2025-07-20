package com.nbeverton.clinsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity
public class ClinsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinsysApplication.class, args);
	}

}
