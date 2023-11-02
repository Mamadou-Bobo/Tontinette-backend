package com.bobo.tontinette.customer.dto.mapper;

import com.bobo.tontinette.customer.dto.RoleDTO;
import com.bobo.tontinette.customer.entity.Role;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Mamadou Bobo on 02/11/2023
 * @project Tontine
 */

@Service
public class RoleDTOMapper implements Function<RoleDTO, Role> {
    @Override
    public Role apply(RoleDTO roleDTO) {
        return new Role(
                roleDTO.id()
        );
    }
}
