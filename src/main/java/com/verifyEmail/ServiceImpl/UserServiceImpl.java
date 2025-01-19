package com.verifyEmail.ServiceImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.verifyEmail.models.VerifyCode;
import com.verifyEmail.repository.VerifyCodeRepository;
import com.verifyEmail.service.EmailService;
import com.verifyEmail.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerifyCodeRepository verifyCodeRepository;

    @Override
    public void sendVerificationCode(String email) throws MailException {
        String code = String.format("%06d", new Random().nextInt(999999));
        String subject = "Email Verification Code";
        String body = "Your verification code is: " + code;
        emailService.sendEmail(email, subject, body);
        VerifyCode store = new VerifyCode();
        store.setEmail(email);
        store.setCode(code);
        verifyCodeRepository.save(store);
    }

}
