package com.jrolab.medic_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jrolab")
public class MedicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicAppApplication.class, args);
	}

}
