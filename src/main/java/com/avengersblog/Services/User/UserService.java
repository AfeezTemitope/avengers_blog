package com.avengersblog.Services.User;

import com.avengersblog.Data.Model.User;
import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.request.User.UserRequest;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Dto.response.User.UserResponse;

public interface UserService {
    UpdateUserProFileResponse updateUserProFile(UpdateUserProFilRequest updateUserProFilRequest);
    UserResponse savedUser(UserRequest userRequest);
    Boolean verifyToken(String token);
    //void ValidationRegistration(UserRequest user);
}
