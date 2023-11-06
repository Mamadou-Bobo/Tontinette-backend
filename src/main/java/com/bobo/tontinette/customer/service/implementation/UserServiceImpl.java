package com.bobo.tontinette.customer.service.implementation;

import com.bobo.tontinette.customer.dto.UserDTO;
import com.bobo.tontinette.customer.dto.mapper.UserDTOMapper;
import com.bobo.tontinette.customer.entity.User;
import com.bobo.tontinette.customer.repository.UserRepository;
import com.bobo.tontinette.customer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Mamadou Bobo on 01/11/2023
 * @project Tontine
 */

@Service
@Slf4j
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserDTOMapper userDTOMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> addUser(UserDTO userDTO) {
        log.info("adding user");

        User user = userDTOMapper.apply(userDTO);

        String message;

        if(userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            message = "Phone number already exists";
            log.error(message);
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            message = "Email address already exists";
            log.error(message);
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }

        if(user.getPassword().isBlank()) {
            message = "Please enter your password";
            log.error(message);
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }

        if(user.getRoles() == null) {
            message = "User should be assigned a role";
            log.error(message);
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setAccountNonExpired(true);
        user.setLocked(false);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);

        userRepository.save(user);

        return new ResponseEntity<>(
                "User account successfully created",
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<String> updateUserAccount(UserDTO userDTO) {
        String message;

        if(userRepository.findByPhoneNumber(userDTO.phoneNumber()).isEmpty()) {
            message = "User does not exist";
            log.error(message);
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        // Make sure the given information match the one on the passport

        if(userRepository.findByEmail(userDTO.email()).isPresent()) {
            message = "Email address already exists";
            log.error(message);
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        User user = userRepository.findByPhoneNumber(userDTO.phoneNumber()).get();

        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());

        userRepository.save(user);

        return ResponseEntity.ok("User account successfully updated");
    }

    @Override
    public ResponseEntity<Object> getUser(String phoneNumber) {
        if(userRepository.findByPhoneNumber(phoneNumber).isEmpty()) {
            log.error("User account not found");
            return new ResponseEntity<>("User account not found", HttpStatus.NOT_FOUND);
        }

        User user = userRepository.findByPhoneNumber(phoneNumber).get();

        UserDTO userDTO = new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail()
        );

        return ResponseEntity.ok(userDTO);
    }
}
