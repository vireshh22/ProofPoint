package com.verifyEmail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.verifyEmail.models.VerifyCode;

public interface VerifyCodeRepository extends MongoRepository<VerifyCode, String> {
    VerifyCode findByEmail(String email);
}
