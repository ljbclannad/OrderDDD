package com.example.orderddd.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderddd.infrastructure.validator.jwt.JwtConfig;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // 这里可以添加用户验证逻辑
        if ("user".equals(username) && "password".equals(password)) {
            return jwtConfig.generateToken(username);
        }
        throw new RuntimeException("Invalid credentials");
    }
}
