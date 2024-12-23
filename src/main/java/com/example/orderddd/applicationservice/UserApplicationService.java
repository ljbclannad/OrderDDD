package com.example.orderddd.applicationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.orderddd.domain.model.aggregate.User;
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

    public User getUser(Integer userId) {
        return userRepository.getById(userId);
    }

    public String loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.getName();
    }

}
