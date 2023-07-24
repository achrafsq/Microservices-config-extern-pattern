package com.example.adminserver;

import com.example.adminserver.entities.Admin;
import com.example.adminserver.repositories.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AdminRepository AdminRepository){
		return  args -> {
	Stream.of("Admin1").forEach(cn -> {
		AdminRepository.save(new Admin(null, cn, "9959959951", "adminemail@gmail.com", "Password1" ));
	});
			AdminRepository.findAll().forEach(System.out::println);
		};
	}
}
