package com.lcwd.services.Impl;

import com.lcwd.entities.User;
import com.lcwd.exceptions.ResourceNotFoundException;
import com.lcwd.repositories.UserRepository;
import com.lcwd.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(@NotNull User user) {
        user.setUserId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
         return userRepository.findAll();
    }

    @Override
    public User getUser(UUID userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException
                                                            ("Resource not Found in Server for the user !! " + userId));
    }
}
