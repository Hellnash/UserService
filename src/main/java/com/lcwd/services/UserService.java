package com.lcwd.services;

import com.lcwd.dtos.UserDto;
import com.lcwd.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUser(UUID userId);
}
