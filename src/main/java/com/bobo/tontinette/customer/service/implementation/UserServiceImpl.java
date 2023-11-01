package com.bobo.tontinette.customer.service.implementation;

import com.bobo.tontinette.customer.dto.UserDTO;
import com.bobo.tontinette.customer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mamadou Bobo on 01/11/2023
 * @project Tontine
 */

@Service
class UserServiceImpl implements UserService {
    @Override
    public ResponseEntity<String> addUser(UserDTO userDTO) {

        return null;
    }
}
