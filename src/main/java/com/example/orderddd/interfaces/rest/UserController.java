package com.example.orderddd.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderddd.applicationservice.UserApplicationService;
import com.example.orderddd.domain.model.aggregate.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserApplicationService userApplicationService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userApplicationService.getUser(userId);
    }
}
