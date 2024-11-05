package com.jrolab.medic_app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jrolab.medic_app")
public class MedicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicAppApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
