package com.bobo.tontinette.customer.service;

import com.bobo.tontinette.customer.dto.UserDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author Mamadou Bobo on 01/11/2023
 * @project Tontine
 */
public interface UserService {
    ResponseEntity<String> addUser(UserDTO userDTO);
    ResponseEntity<String> updateUserAccount(UserDTO userDTO);
}
