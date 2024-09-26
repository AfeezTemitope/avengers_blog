package com.avengersblog.Services;

import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;

public interface UserService {
    UpdateUserProFileResponse updateUserProFile(UpdateUserProFilRequest updateUserProFilRequest);
}
