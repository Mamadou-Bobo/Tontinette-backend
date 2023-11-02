package com.bobo.tontinette.customer.service.implementation;

import com.bobo.tontinette.customer.dto.UserDTO;
import com.bobo.tontinette.customer.dto.mapper.UserDTOMapper;
import com.bobo.tontinette.customer.entity.User;
import com.bobo.tontinette.customer.repository.UserRepository;
import com.bobo.tontinette.customer.service.UserService;
import com.bobo.tontinette.shared.utils.ResponseHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ResponseHandlerService responseHandlerService;

    @Override
    public ResponseEntity<String> addUser(UserDTO userDTO) {
        log.info("adding user");

        String message;

        User user = userDTOMapper.apply(userDTO);

        if(userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            message = "Phone number already exists";
            log.error(message);
            return responseHandlerService.handleError(message,HttpStatus.CONFLICT);
        }

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            message = "Email address already exists";
            log.error(message);
            return responseHandlerService.handleError(message,HttpStatus.CONFLICT);
        }

        if(user.getPassword().isBlank()) {
            message = "Please enter your password";
            log.error(message);
            return responseHandlerService.handleError(message,HttpStatus.BAD_REQUEST);
        }

        if(user.getRoles() == null) {
            message = "User role cannot be empty";
            log.error(message);
            return responseHandlerService.handleError(message,HttpStatus.BAD_REQUEST);
        }

        userRepository.save(user);

        return responseHandlerService.handleSuccess("User account successfully created", HttpStatus.CREATED);
    }
}
