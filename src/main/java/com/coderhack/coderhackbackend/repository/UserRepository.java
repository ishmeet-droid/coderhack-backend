package com.coderhack.coderhackbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.coderhack.coderhackbackend.entity.User;

public interface UserRepository extends MongoRepository<User, Long>  {
    
}
