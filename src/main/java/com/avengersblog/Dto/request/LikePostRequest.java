package com.avengersblog.Dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikePostRequest {
    private Long postId;
    private Long userId;
}
