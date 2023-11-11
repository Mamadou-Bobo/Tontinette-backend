package com.bobo.tontinette.customer.service;

import com.bobo.tontinette.customer.dto.UserDTO;
import com.bobo.tontinette.customer.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author Mamadou Bobo on 01/11/2023
 * @project Tontine
 */
public interface UserService {
    ResponseEntity<String> addUser(UserDTO userDTO);
    ResponseEntity<String> updateUserAccount(UserDTO userDTO);
    ResponseEntity<Object> getUser(String phoneNumber);
    User findByPhoneNumber(String phoneNumber);
}
