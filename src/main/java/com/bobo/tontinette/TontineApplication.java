package com.bobo.tontinette;

import com.bobo.tontinette.customer.entity.Privilege;
import com.bobo.tontinette.customer.entity.Role;
import com.bobo.tontinette.customer.repository.PrivilegeRepository;
import com.bobo.tontinette.customer.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.bobo.tontinette.shared.utils.Constant.*;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class TontineApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TontineApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(RoleRepository roleRepository,
							 PrivilegeRepository privilegeRepository) {
		return args -> {
			Collection<Privilege> privileges = new ArrayList<>();

			privileges.add(new Privilege(READ));
			privileges.add(new Privilege(WRITE));
			privileges.add(new Privilege(DELETE));

			privilegeRepository.saveAll(privileges);

			List<Role> roles = new ArrayList<>();

			roles.add(new Role(ADMIN,privileges));

			roleRepository.saveAll(roles);
		};
	}

}
