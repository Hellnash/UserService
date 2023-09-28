package com.lcwd.services;

import com.lcwd.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUser(UUID userId);
}
