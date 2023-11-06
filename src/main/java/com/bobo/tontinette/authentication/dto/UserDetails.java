package com.bobo.tontinette.authentication.dto;

import com.bobo.tontinette.customer.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Mamadou Bobo on 04/11/2023
 * @project Tontine
 */
public record UserDetails(User user) implements org.springframework.security.core.userdetails.UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}
