package com.avengersblog.Services;

import com.avengersblog.Data.Model.User;
import com.avengersblog.Data.Repository.UserRepository;
import com.avengersblog.Dto.request.User.LoginRequest;
import com.avengersblog.Dto.request.User.UpdateUserProFilRequest;
import com.avengersblog.Dto.request.User.UserRequest;
import com.avengersblog.Dto.response.LoginResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Dto.response.User.UserResponse;
import com.avengersblog.Services.User.UserService;
import com.avengersblog.Services.User.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;



    @Test
    void createUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("rebzyobanla2323@gmail.com");
        userRequest.setUserName("Rebzyspecial");
        userRequest.setLastName("obanla");
        userRequest.setFirstName("rebbecca");
        userRequest.setPassword("56432");
        UserResponse userResponse = userService.savedUser(userRequest);
        System.out.println(userRequest);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getMessage()).isEqualTo("You Register Successful");
    }
    @Test
    public  void testThatUserCanLogin(){
        LoginResponse loginResponse = login();
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isEqualTo("Successfully logged in");
    }

    private LoginResponse login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Rebzyspecial");
        loginRequest.setPassword("56432");
        LoginResponse loginResponse = userService.login(loginRequest);
        return loginResponse;
    }

    @Test
    public void testThatMethodCanUpdateUser(){
        UpdateUserProFilRequest request = new UpdateUserProFilRequest();
        request.setEmail("last2323@gmail.com");
        request.setUserName("olodo");
        request.setLastName("ola");
        request.setFirstName("olodo");
        request.setPassword("56432");
        UpdateUserProFileResponse response = userService.updateUserProFile(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Successfully updated user profile");

    }
}
