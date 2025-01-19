package com.verifyEmail.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "users")
@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String city;
    private String college;
}
