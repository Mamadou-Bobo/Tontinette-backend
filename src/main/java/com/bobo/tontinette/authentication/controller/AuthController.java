package com.bobo.tontinette.authentication.controller;

import com.bobo.tontinette.authentication.dto.AuthDTO;
import com.bobo.tontinette.authentication.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bobo.tontinette.shared.utils.Constant.BASE_API;

/**
 * @author Mamadou Bobo on 05/11/2023
 * @project Tontine
 */

@RestController
@RequestMapping(BASE_API + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthDTO authDTO,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        return authService.login(authDTO,request,response);
    }

}
