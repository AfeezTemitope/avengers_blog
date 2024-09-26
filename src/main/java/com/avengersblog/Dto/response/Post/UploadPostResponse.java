package com.avengersblog.Dto.response.Post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UploadPostResponse {
    private String message;

    public UploadPostResponse(String message) {
        this.message = message;
    }
}
