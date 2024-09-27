package com.avengersblog.Controller;

import com.avengersblog.Data.Model.HttResponse;
import com.avengersblog.Data.Model.User;
import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.request.User.UserRequest;
import com.avengersblog.Dto.response.ApiResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Dto.response.User.UserResponse;
import com.avengersblog.Services.User.UserServiceImpl;
import lombok.RequiredArgsConstructor;

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
//    @Autowired
//    private final userService;
    private final UserServiceImpl userService;

    @PatchMapping("/updateUserProFile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateUserProFilRequest updateUserProFilRequest) {
        try {
            UpdateUserProFileResponse updateUserProFileResponse = userService.updateUserProFile(updateUserProFilRequest);
            return new ResponseEntity<>(new ApiResponse(true, updateUserProFileResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<HttResponse> savedUser(@RequestBody UserRequest user){
        UserResponse userResponse  = userService.savedUser(user);
        return ResponseEntity.created(URI.create("")).body(
                HttResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", userResponse))
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()

        );
    }
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


