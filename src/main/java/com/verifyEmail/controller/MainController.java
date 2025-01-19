package com.verifyEmail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verifyEmail.models.User;
import com.verifyEmail.models.VerifyCode;
import com.verifyEmail.repository.UserRepository;
import com.verifyEmail.repository.VerifyCodeRepository;
import com.verifyEmail.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerifyCodeRepository verifyCodeRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/send-code")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email, HttpSession session)
            throws MailException {
        userService.sendVerificationCode(email);
        session.setAttribute("email", email);
        return ResponseEntity.ok("Verification email sent successfully to " + email);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam String code, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return ResponseEntity.status(400).body("Email not found in session.");
        }
        VerifyCode storedCode = verifyCodeRepository.findByEmail(email);

        if (storedCode != null && storedCode.getCode().equals(code)) {
            User verifiedUser = new User();
            verifiedUser.setId(3);
            verifiedUser.setEmail(email);
            verifiedUser.setName(email);
            verifiedUser.setCity("Solapur");
            verifiedUser.setCollege("WIT");
            userRepository.save(verifiedUser);

            session.removeAttribute("email");
            verifyCodeRepository.delete(storedCode);
            return ResponseEntity.ok("Email Verified Successfully");
        } else {
            return ResponseEntity.status(400).body("Invalid Verification Code.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User u = this.userRepository.save(user);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<String> handleMailException(MailException ex) {
        return ResponseEntity.status(500).body("Failed to send email");
    }

}
