package com.lcwd.UserService.services;

import com.lcwd.UserService.entities.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);
}
