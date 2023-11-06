package com.bobo.tontinette.authentication.utils.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;

/**
 * @author Mamadou Bobo on 04/11/2023
 * @project Tontine
 */

@Component(value = "customLogoutHandler")
@RequiredArgsConstructor
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {

    private final FindByIndexNameSessionRepository<? extends Session> redisIndexedSessionRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String id = request.getSession(false).getId();

        if (id != null && this.redisIndexedSessionRepository.findById(id) != null) {
            this.redisIndexedSessionRepository.deleteById(id);
            log.info("User successfully logged out");
        }
    }

}
