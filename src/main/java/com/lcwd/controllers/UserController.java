package com.lcwd.controllers;

import com.lcwd.dtos.UserDto;
import com.lcwd.entities.User;
import com.lcwd.exceptions.ResourceNotFoundException;
import com.lcwd.services.Impl.UserServiceImpl;
import com.lcwd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser (@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getAUser(@PathVariable UUID userId){
            return new ResponseEntity<>(userService.getUser(userId), HttpStatus.FOUND);

    }
}
