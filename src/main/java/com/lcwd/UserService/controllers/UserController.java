package com.lcwd.UserService.controllers;

import com.lcwd.UserService.entities.User;
import com.lcwd.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAuthority;

@RestController
@RequestMapping("/users")
@Slf4j
@SuppressWarnings("unused")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('Admin')") //only admins can create the users
    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Users')") //admins and users can get all users
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Users')") //admins and users can get a user
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getAUser(@PathVariable String userId){
            return new ResponseEntity<>(userService.getUser(userId), HttpStatus.FOUND);

    }

    //run if the above API which is dependent on Rating service will fail
    public ResponseEntity<User> ratingHotelFallback(Exception ex){
        log.error("Dependent Services are down due to "+ex.getMessage());
        User dummyUser = User.builder().userId("12345").name("dummy").email("dummy@fake.in")
                             .about("This is a dummy user as the rating service is down").build();
        return new ResponseEntity<>(dummyUser, HttpStatus.OK);
    }
}
