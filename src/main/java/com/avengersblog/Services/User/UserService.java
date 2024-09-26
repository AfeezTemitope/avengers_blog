package com.avengersblog.Services.User;

import com.avengersblog.Dto.request.LoginRequest;
import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.response.LoginResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;

public interface UserService {
    UpdateUserProFileResponse updateUserProFile(UpdateUserProFilRequest updateUserProFilRequest);
    LoginResponse login(LoginRequest loginRequest);
}
