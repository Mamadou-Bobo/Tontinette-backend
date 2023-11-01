package com.bobo.tontinette.customer.repository;

import com.bobo.tontinette.customer.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mamadou Bobo on 01/11/2023
 * @project Tontine
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
