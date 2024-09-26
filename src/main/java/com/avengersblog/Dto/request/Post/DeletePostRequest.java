package com.avengersblog.Dto.request.Post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeletePostRequest {
    String userName;
    String password;
    String title;
}
