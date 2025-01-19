package com.verifyEmail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.verifyEmail.models.User;

public interface UserRepository extends MongoRepository<User,Integer>{
    
}
