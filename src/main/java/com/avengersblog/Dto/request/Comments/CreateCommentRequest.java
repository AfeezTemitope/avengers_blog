package com.avengersblog.Dto.request.Comments;

import com.avengersblog.Data.Model.Post;
import com.avengersblog.Data.Model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class CreateCommentRequest {
    private String commentId;
    private String comment;
    private LocalDateTime createdAt;
    private Long userId;
    private Long postId;
}
