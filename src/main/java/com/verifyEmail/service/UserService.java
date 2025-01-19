package com.verifyEmail.service;

import org.springframework.mail.MailException;

public interface UserService {
    public void sendVerificationCode(String email) throws MailException;
}
