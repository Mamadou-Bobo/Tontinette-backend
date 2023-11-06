package com.bobo.tontinette.authentication.service;

import com.bobo.tontinette.authentication.dto.AuthDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @author Mamadou Bobo on 04/11/2023
 * @project Tontine
 */

@Service
@Setter
public class AuthService {

    @Value(value = "${custom.max.session}")
    private int maxSession;

    private final SecurityContextRepository securityContextRepository;
    private final AuthenticationManager authenticationManager;
    private final RedisIndexedSessionRepository redisIndexedSessionRepository;
    private final SessionRegistry sessionRegistry;
    private final SecurityContextHolderStrategy securityContextHolderStrategy;

    public AuthService(SecurityContextRepository securityContextRepository,
                       AuthenticationManager authenticationManager,
                       RedisIndexedSessionRepository redisIndexedSessionRepository,
                       SessionRegistry sessionRegistry) {
        this.securityContextRepository = securityContextRepository;
        this.authenticationManager = authenticationManager;
        this.redisIndexedSessionRepository = redisIndexedSessionRepository;
        this.sessionRegistry = sessionRegistry;
        this.securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    }

    public ResponseEntity<String> login(AuthDTO authDTO,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        // Validate User credentials
        Authentication authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken.unauthenticated(
                authDTO.phoneNumber(), authDTO.password()));

        // Validate session constraint is not exceeded
        validateMaxSession(authentication);

        // Create a new context
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        // Update SecurityContextHolder and Strategy
        this.securityContextHolderStrategy.setContext(context);
        this.securityContextRepository.saveContext(context, request, response);

        return ResponseEntity.ok("User successfully authenticated");
    }

    private void validateMaxSession(Authentication authentication) {
        // If max session is negative means unlimited session
        if (maxSession <= 0) {
            return;
        }

        var principal = (UserDetails) authentication.getPrincipal();
        List<SessionInformation> sessions = this.sessionRegistry.getAllSessions(principal, false);

        if (sessions.size() >= maxSession) {
            sessions.stream()
                    // Gets the oldest session
                    .min(Comparator.comparing(SessionInformation::getLastRequest))
                    .ifPresent(sessionInfo ->
                                    this.redisIndexedSessionRepository
                                            .deleteById(sessionInfo.getSessionId()));
        }
    }
}
