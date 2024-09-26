package com.avengersblog.Services.User;

import com.avengersblog.Data.Model.User;
import com.avengersblog.Data.Repository.UserRepository;
import com.avengersblog.Dto.request.LoginRequest;
import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.response.LoginResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UpdateUserProFileResponse updateUserProFile(UpdateUserProFilRequest updateUserProFilRequest) {
        findUserByEmail(updateUserProFilRequest.getEmail());
        User user = new User();
        user.setFirstName(updateUserProFilRequest.getFirstName());
        user.setLastName(updateUserProFilRequest.getLastName());
        user.setEmail(updateUserProFilRequest.getEmail());
        user.setPassword(updateUserProFilRequest.getPassword());
        userRepository.save(user);

        UpdateUserProFileResponse updateUserProFileResponse = new UpdateUserProFileResponse();
        updateUserProFileResponse.setEmail(updateUserProFilRequest.getEmail());
        updateUserProFileResponse.setMessage("Successfully updated user profile");
        return updateUserProFileResponse;

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = findUserByUsername(loginRequest.getUsername());
        PasswordValidation(user, loginRequest.getPassword());
        user.setLoggedIn(true);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successfully logged in");
        return loginResponse;
    }

    private void PasswordValidation(User user, String password) {
        if (!password.equals(user.getPassword()) && password.trim().isEmpty()) {
            throw new RuntimeException("Invalid Credentials");
        }
    }


    private void findUserByEmail(String email) {
        userRepository.findUserByEmail(email).
                orElseThrow(() -> new RuntimeException(email + "User Not Found"));

    }

    private User findUserByUsername(String username) {
        return userRepository.findUserByEmail(username).
                orElseThrow(() -> new RuntimeException(username + "User Not Found"));
    }
}
