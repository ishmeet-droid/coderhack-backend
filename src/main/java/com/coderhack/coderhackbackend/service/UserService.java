package com.coderhack.coderhackbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coderhack.coderhackbackend.entity.User;
import com.coderhack.coderhackbackend.exceptions.UserNotFoundException;
import com.coderhack.coderhackbackend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){

        this.userRepository = userRepository;
    }
   
    public List<User> getRegisteredUsers(){

        return userRepository.findAll();
    
    }
    
    public User getRegisteredUserById(String userId) throws UserNotFoundException{

        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    
    }

    public User registerNewUser(User user){

        return userRepository.save(user);
    
    }
    
}
