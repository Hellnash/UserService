package com.lcwd.UserService.services.Impl;

import com.lcwd.UserService.entities.Hotel;
import com.lcwd.UserService.entities.Rating;
import com.lcwd.UserService.entities.User;
import com.lcwd.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.UserService.repositories.UserRepository;
import com.lcwd.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().peek(user -> {
            Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(), Rating[].class);
            List<Rating> userRatings = Arrays.stream(ratings).toList();
            List<Rating> ratingList = userRatings.stream().peek(rating -> {
                Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                rating.setHotel(hotel);
            }).toList();
            user.setRatings(ratingList);
        }).collect(Collectors.toList());
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Resource not Found in Server for the user !! " + userId));
        Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(userRatings).toList();
        user.setRatings(ratings);
        List<Rating> collectedRatings = ratings.stream().peek(rating -> {
            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            rating.setHotel(hotel);
        }).collect(Collectors.toList());
        user.setRatings(collectedRatings);
        return user;
    }
}
