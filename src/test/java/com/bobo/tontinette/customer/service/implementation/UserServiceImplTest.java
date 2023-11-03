package com.bobo.tontinette.customer.service.implementation;

import com.bobo.tontinette.customer.dto.RoleDTO;
import com.bobo.tontinette.customer.dto.UserDTO;
import com.bobo.tontinette.customer.dto.mapper.RoleDTOMapper;
import com.bobo.tontinette.customer.dto.mapper.UserDTOMapper;
import com.bobo.tontinette.customer.entity.Role;
import com.bobo.tontinette.customer.entity.User;
import com.bobo.tontinette.customer.repository.UserRepository;
import com.bobo.tontinette.shared.utils.ResponseHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Mamadou Bobo on 02/11/2023
 * @project Tontine
 */

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserDTOMapper userDTOMapper;

    @InjectMocks
    private RoleDTOMapper roleDTOMapper;

    @Test
    void shouldRegisterANewUser() {
        // given
        Collection<RoleDTO> roles = new ArrayList<>();
        roles.add(new RoleDTO(1L));

        UserDTO userDTO = new UserDTO(
                "John",
                "Doe",
                "1234",
                "+224647583745",
                "test@tontinette.com",
                roles);

//        given(roleDTOMapper.apply(new RoleDTO(1L))).willReturn(new Role());
        given(userDTOMapper.apply(userDTO)).willReturn(new User());

        // when

        // Mock UserRepository behavior
        when(userRepository.save(any(User.class))).then(returnsFirstArg());

        // then
        ResponseEntity<String> savedUser = userServiceImpl.addUser(userDTO);
        assertThat(savedUser).isNotNull();

        // verify
        verify(userRepository, times(1)).save(any(User.class));
    }

}