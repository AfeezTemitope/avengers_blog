package com.avengersblog.Dto.response.Post;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeletePostResponse {
    private String message;

    public DeletePostResponse(String message) {
        this.message = message;
    }
}
