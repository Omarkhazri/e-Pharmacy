package com.sesame.e_pharmacy;

import com.sesame.e_pharmacy.entity.Role;
import com.sesame.e_pharmacy.enums.ERole;
import com.sesame.e_pharmacy.repositores.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EPharmacyApplication {

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {
		return args -> {
			ERole[] roles = ERole.values();
			for (ERole role : roles) {
				Role roleEntity = roleRepository.findByName(role)
						.orElse(new Role(role));
				roleRepository.save(roleEntity);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(EPharmacyApplication.class, args);
	}

}
