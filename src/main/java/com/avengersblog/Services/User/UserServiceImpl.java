package com.avengersblog.Services.User;

import com.avengersblog.Data.Model.Confirmation;
import com.avengersblog.Data.Model.User;
import com.avengersblog.Data.Repository.ConfirmationRepository;
import com.avengersblog.Data.Repository.UserRepository;
import com.avengersblog.Dto.request.LoginRequest;
import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.request.User.UserRequest;
import com.avengersblog.Dto.response.LoginResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Dto.response.User.UserResponse;
import com.avengersblog.Services.Email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

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

    @Override
    public UserResponse savedUser(UserRequest userRequest) {
        RegistrationValidation(userRequest);
        if(userRepository.findByEmail(userRequest.getEmail())!=null){
            throw new RuntimeException("user already exist");
        }
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUserName(userRequest.getUserName());
        userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);
        userResponse.setMessage("You Register Successful");
        emailService.sendSimpleMailMessage(user.getUserName(), user.getEmail(), confirmation.getToken());
        return userResponse;
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
    private void RegistrationValidation(UserRequest userRequest){
       if (userRequest.getUserName().trim().isEmpty()||userRequest.getFirstName().trim().isEmpty()||
               userRequest.getLastName().trim().isEmpty() ||
                userRequest.getPassword().trim().isEmpty()|| userRequest.getEmail().trim().isEmpty()){
            throw new RuntimeException("cannot be empty");
       }

    }
    private String countLike(String name){
        return name;
    }

}
