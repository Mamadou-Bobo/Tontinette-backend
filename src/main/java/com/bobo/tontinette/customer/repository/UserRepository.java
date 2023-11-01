package com.bobo.tontinette.customer.repository;

import com.bobo.tontinette.customer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mamadou Bobo on 31/10/2023
 * @project Tontine
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
