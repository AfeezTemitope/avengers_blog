package com.avengersblog.Controller;

import com.avengersblog.Dto.request.User.LoginRequest;
import com.avengersblog.Dto.request.User.UpdateUserProFilRequest;
import com.avengersblog.Data.Model.HttResponse;
import com.avengersblog.Data.Model.User;
//import com.avengersblog.Dto.request.LoginRequest;
//import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.request.User.UserRequest;
import com.avengersblog.Dto.response.ApiResponse;
import com.avengersblog.Dto.response.LoginResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Dto.response.User.UserResponse;
import com.avengersblog.Services.User.UserService;
import com.avengersblog.Services.User.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    private final UserService userService;


    @PatchMapping("/updateUserProFile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateUserProFilRequest updateUserProFilRequest) {
        try {
            UpdateUserProFileResponse updateUserProFileResponse = userService.updateUserProFile(updateUserProFilRequest);
            return new ResponseEntity<>(new ApiResponse(true, updateUserProFileResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = userService.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, loginResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<HttResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse newUser = userService.savedUser(userRequest);
        return ResponseEntity.created(URI.create("")).body(
                HttResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", newUser))
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()

        );
    }
    @PostMapping("/confirm")
    public ResponseEntity<HttResponse>confirmationUserAccount(@RequestParam("token")String token){
        Boolean isSuccess = userService.verifyToken(token);
        return ResponseEntity.ok().body(
                HttResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("success", isSuccess))
                        .message("Account Verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()

        );
    }

}




