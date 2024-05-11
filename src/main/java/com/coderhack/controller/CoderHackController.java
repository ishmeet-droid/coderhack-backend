package com.coderhack.controller;

import org.springframework.web.bind.annotation.RestController;

import com.coderhack.coderhackbackend.entity.User;

import org.springframework.web.bind.annotation.RequestMapping;
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

    public static final String PATH = "/coderhack/users";


    @GetMapping()
    public ResponseEntity<?> getAllUsers() {

        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserWithId(@PathVariable Long userId) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<?> enrollUserToContest(@RequestBody User user) {
     
        return null;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserDetails(@PathVariable Long userId, @RequestBody User user) {
     
        return null;
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> removeUserFromContest(@PathVariable Long userId) {
     
        return null;
    }

    
    
    
    
}
