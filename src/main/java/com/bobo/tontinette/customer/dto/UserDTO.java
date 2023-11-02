package com.bobo.tontinette.customer.dto;

import java.util.Collection;

/**
 * @author Mamadou Bobo on 01/11/2023
 * @project Tontine
 */
public record UserDTO(
        String firstName,
        String lastName,
        String password,
        String phoneNumber,
        String email,
        Collection<RoleDTO> roles) {
}
