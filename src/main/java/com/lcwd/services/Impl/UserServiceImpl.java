package com.lcwd.services.Impl;

import com.lcwd.dtos.UserDto;
import com.lcwd.entities.User;
import com.lcwd.exceptions.ResourceNotFoundException;
import com.lcwd.repositories.UserRepository;
import com.lcwd.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public UserDto createUser(@NotNull UserDto userDto) {
        userDto.setUserId(UUID.randomUUID());
        User savedUser = userRepository.save(mapper.map(userDto, User.class));
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            UserDto userDto = mapper.map(user, UserDto.class);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto getUser(UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException
                                                            ("Resource not Found in Server for the user !! " + userId));
        return mapper.map(user, UserDto.class);
    }
}
