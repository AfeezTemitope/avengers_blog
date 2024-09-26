package com.avengersblog.Controller;

import com.avengersblog.Dto.request.LoginRequest;
import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.response.ApiResponse;
import com.avengersblog.Dto.response.LoginResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

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
}
