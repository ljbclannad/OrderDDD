package com.example.orderddd.applicationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderddd.domain.model.aggregate.User;
import com.example.orderddd.domain.service.UserDomainService;
import com.example.orderddd.infrastructure.repository.UserRepository;

/**
 * 用户应用服务
 * 
 * @author lejb
 * @version 1.0
 */
@Service
public class UserApplicationService {

    @Autowired
    private UserRepository userRepository;

    private UserDomainService userDomainService;

    public User getUser(String userId) {
        return userRepository.findById(userId);
    }

}
