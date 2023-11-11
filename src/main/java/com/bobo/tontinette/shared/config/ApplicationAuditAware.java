package com.bobo.tontinette.shared.config;

import com.bobo.tontinette.authentication.dto.UserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Mamadou Bobo on 11/11/2023
 * @project Tontine
 */

public class ApplicationAuditAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if(authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        UserDetails user = (UserDetails) authentication.getPrincipal();

        System.out.println(user.user().getId());

        return Optional.ofNullable(user.user().getId());
    }
}
