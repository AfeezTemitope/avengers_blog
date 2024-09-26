package com.avengersblog.Dto.request.Post;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadPostRequest {
    private String title;
    private String caption;
    private MultipartFile image;
}
