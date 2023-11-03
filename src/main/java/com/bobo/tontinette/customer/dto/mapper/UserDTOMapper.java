package com.bobo.tontinette.customer.dto.mapper;

import com.bobo.tontinette.customer.dto.UserDTO;
import com.bobo.tontinette.customer.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Mamadou Bobo on 01/11/2023
 * @project Tontine
 */

@Service
@RequiredArgsConstructor
public class UserDTOMapper implements Function<UserDTO, User> {

    private final RoleDTOMapper roleDTOMapper;

    @Override
    public User apply(UserDTO userDTO) {
        return new User(
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.password(),
                userDTO.phoneNumber(),
                userDTO.email(),
                userDTO.roles().stream().map(roleDTOMapper).collect(Collectors.toList())
        );
    }
}
