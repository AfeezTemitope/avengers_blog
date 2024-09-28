package com.avengersblog.Controller;
import com.avengersblog.Data.Model.GoogleSignInVerifier;
import com.avengersblog.Data.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.avengersblog.Data.Model.User;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/auth/google")
    public Map<String, Object> googleSignIn(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        try {

            User googleUser = GoogleSignInVerifier.verifyAndExtractUserData(token);

            User existingUser = userRepository.findByEmail(googleUser.getEmail());

            if (existingUser == null) {
                userRepository.save(googleUser);
                return Map.of("success", true, "message", "User registered successfully via Google!");
            } else {
                return Map.of("success", true, "message", "User logged in via Google!");
            }

        } catch (Exception e) {
            return Map.of("success", false, "message", "Google login failed: " + e.getMessage());
        }
    }
}
