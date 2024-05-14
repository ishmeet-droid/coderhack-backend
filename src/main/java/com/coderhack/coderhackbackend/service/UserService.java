package com.coderhack.coderhackbackend.service;

import java.util.List;

import org.springframework.data.domain.Sort;
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

       // return userRepository.findByOrderByScoreDesc();
          return userRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
    }
    
    public User getRegisteredUserById(String userId) throws UserNotFoundException{

        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    
    }

    public User registerNewUser(User user){

        return userRepository.save(user);
    
    }

    public User updateUserScore(String userId, int newScore) {
        if (newScore < 0 || newScore > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        User user = userRepository.findById(userId).get();
        user.setScore(newScore);
       // user.getBadges().clear(); // Clear existing badges before re-awarding
        user.awardBadges();
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
    
}
