package com.avengersblog.Dto.request.Post;

import com.avengersblog.Data.Model.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadPostRequest {
    private String title;
    private String caption;
    private MultipartFile image;
    private Category category;
}
