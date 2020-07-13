package com.zf.api.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/index")
    public void Index() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

    }
}
