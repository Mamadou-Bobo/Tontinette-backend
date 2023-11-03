package com.bobo.tontinette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TontineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TontineApplication.class, args);
	}

	/*
	@Override
	public void run(String... args) {

		Collection<Privilege> privileges = new ArrayList<>();

		privileges.add(new Privilege(READ));
		privileges.add(new Privilege(WRITE));
		privileges.add(new Privilege(DELETE));

		privilegeRepository.saveAll(privileges);

		List<Role> roles = new ArrayList<>();

		roles.add(new Role(ADMIN,privileges));

		roleRepository.saveAll(roles);
	}
	 */
}
