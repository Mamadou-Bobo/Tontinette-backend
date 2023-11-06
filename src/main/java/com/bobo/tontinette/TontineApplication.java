package com.bobo.tontinette;

import com.bobo.tontinette.customer.entity.Privilege;
import com.bobo.tontinette.customer.entity.Role;
import com.bobo.tontinette.customer.repository.PrivilegeRepository;
import com.bobo.tontinette.customer.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.bobo.tontinette.shared.utils.Constant.*;

@SpringBootApplication
@RequiredArgsConstructor
public class TontineApplication implements CommandLineRunner {

	private final PrivilegeRepository privilegeRepository;
	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(TontineApplication.class, args);
	}

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

}
