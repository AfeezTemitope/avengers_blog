package com.avengersblog.Services.User;

import com.avengersblog.Dto.request.User.LikePostRequest;
import com.avengersblog.Dto.request.User.LoginRequest;
import com.avengersblog.Dto.request.User.SharePostRequest;
import com.avengersblog.Dto.request.User.UpdateUserProFilRequest;
import com.avengersblog.Dto.response.LikePostResponse;
import com.avengersblog.Dto.request.User.UserRequest;
import com.avengersblog.Dto.response.LoginResponse;
import com.avengersblog.Dto.response.SharedPostResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Dto.response.User.UserResponse;

public interface UserService {
    UpdateUserProFileResponse updateUserProFile(UpdateUserProFilRequest updateUserProFilRequest);
    LoginResponse login(LoginRequest loginRequest);
    SharedPostResponse sharePost (Long userId, Long postId, SharePostRequest sharePostRequest);
    LikePostResponse likePost (Long userId, Long postId, LikePostRequest likePostRequest);
    UserResponse savedUser(UserRequest userRequest);

}
