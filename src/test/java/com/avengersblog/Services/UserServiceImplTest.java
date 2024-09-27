package com.avengersblog.Services;

import com.avengersblog.Data.Model.User;
import com.avengersblog.Dto.request.User.UserRequest;
import com.avengersblog.Dto.response.User.UserResponse;
import com.avengersblog.Services.User.UserService;
import com.avengersblog.Services.User.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
        userRequest.setEmail("rebzyspecial2323@gmail.com");
        userRequest.setLastName("Rebzy");
        userRequest.setFirstName("oba");
        userRequest.setPassword("12345");

        UserResponse userResponse = userService.savedUser(userRequest);
        System.out.println(userRequest);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getMessage()).isEqualTo("You have Register Successfully");

    }
}
