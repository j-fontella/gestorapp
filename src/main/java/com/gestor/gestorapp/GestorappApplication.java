package com.gestor.gestorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestorappApplication {

	public static void main(String[] args) {
		iniciarAplicacao(args);
	}
	
	public static void iniciarAplicacao(String[] args) {
		SpringApplication.run(GestorappApplication.class, args);

	}

}
