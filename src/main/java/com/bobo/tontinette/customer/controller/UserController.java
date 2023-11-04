package com.bobo.tontinette.customer.controller;

import com.bobo.tontinette.customer.dto.UserDTO;
import com.bobo.tontinette.customer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bobo.tontinette.shared.utils.Constant.BASE_API;

/**
 * @author Mamadou Bobo on 31/10/2023
 * @project Tontine
 */

@RestController
@RequestMapping(BASE_API+"/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO) {
        return userService.updateUserAccount(userDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUser(@RequestBody UserDTO userDTO) {
        return userService.getUser(userDTO.phoneNumber());
    }
}
