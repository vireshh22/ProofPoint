package com.verifyEmail.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "verify_code")
@Data
public class VerifyCode {
    @Id
    private String email;
    private String code;
}
