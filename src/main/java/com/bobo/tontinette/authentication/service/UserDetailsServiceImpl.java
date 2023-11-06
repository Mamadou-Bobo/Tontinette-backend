package com.bobo.tontinette.authentication.service;

import com.bobo.tontinette.customer.repository.UserRepository;

import com.bobo.tontinette.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Mamadou Bobo on 04/11/2023
 * @project Tontine
 */

@Service(value = "detailService")
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return this.userRepository
                .findByPhoneNumber(phoneNumber)
                .map(com.bobo.tontinette.authentication.dto.UserDetails::new)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
