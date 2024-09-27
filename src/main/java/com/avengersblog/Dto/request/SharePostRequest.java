package com.avengersblog.Dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharePostRequest {
    private Long postId;
    private Long receiverId;
}
