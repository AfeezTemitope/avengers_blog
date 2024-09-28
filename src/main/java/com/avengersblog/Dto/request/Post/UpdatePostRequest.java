package com.avengersblog.Dto.request.Post;

import com.avengersblog.Data.Model.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePostRequest {
    private String title;
    private String caption;
    private Category category;
}
