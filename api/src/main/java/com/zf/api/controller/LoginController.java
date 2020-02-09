package com.zf.api.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/login")
    public void login() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

    }
}
