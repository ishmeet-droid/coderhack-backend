package com.coderhack.coderhackbackend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderhack.coderhackbackend.entity.User;
import com.coderhack.coderhackbackend.exceptions.UserNotFoundException;
import com.coderhack.coderhackbackend.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(CoderHackController.PATH)
public class CoderHackController {

    private static final Logger LOG = LoggerFactory.getLogger(CoderHackController.class);
    public static final String PATH = "/coderhack/users";

    private final UserService userService;

    private CoderHackController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsersBasedOnScores() {

        try {
            return ResponseEntity.ok(userService.getRegisteredUsers());
        } catch (Exception e) {
            
            LOG.error("exception: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserWithId(@PathVariable String userId) {

        LOG.info("userId: {}", userId);
       
        try {

            return ResponseEntity.ok(userService.getRegisteredUserById(userId));
        } catch (UserNotFoundException e) {

            LOG.error("exception: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
            // return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> enrollUserToContest(@RequestBody User newUserRequest,
            UriComponentsBuilder ucb) {

        User savedUser = userService.registerNewUser(newUserRequest);
        URI locationOfNewUser = ucb
                .path("/coderhack/users/{id}")
                .buildAndExpand(savedUser.getUserId()).toUri();
        return ResponseEntity.created(locationOfNewUser).build();

    }

    @PutMapping("/{userId}")
    public User updateUserScore(@PathVariable String userId, @RequestParam int score) {
        return userService.updateUserScore(userId, score);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    
    
    
    
}
