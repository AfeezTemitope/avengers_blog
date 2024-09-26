package com.avengersblog.Dto.response.Post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePostResponse {
    private String message;

    public UpdatePostResponse(String message) {
        this.message = message;
    }
}
