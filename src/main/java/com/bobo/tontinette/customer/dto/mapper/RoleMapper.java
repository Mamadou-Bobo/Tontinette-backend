package com.bobo.tontinette.customer.dto.mapper;

import com.bobo.tontinette.customer.dto.RoleDTO;
import com.bobo.tontinette.customer.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Mamadou Bobo on 02/11/2023
 * @project Tontine
 */

@Service
public class RoleMapper implements Function<Role, RoleDTO> {

    @Override
    public RoleDTO apply(Role role) {
        return new RoleDTO(role.getId());
    }
}
