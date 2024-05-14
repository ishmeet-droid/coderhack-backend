package com.coderhack.coderhackbackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.coderhack.coderhackbackend.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

    List<User> findByOrderByScoreDesc();
}
