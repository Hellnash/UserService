package com.lcwd.UserService.services.Impl;

import com.lcwd.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.UserService.entities.User;
import com.lcwd.UserService.repositories.UserRepository;
import com.lcwd.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
         return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Resource not Found in Server for the user !! " + userId));
    }
}
