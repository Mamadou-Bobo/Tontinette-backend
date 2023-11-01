package com.bobo.tontinette.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bobo.tontinette.shared.utils.Constant.BASE_API;

/**
 * @author Mamadou Bobo on 31/10/2023
 * @project Tontine
 */

@RestController
@RequestMapping(BASE_API)
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }

}
