package com.avengersblog.Dto.request.Comments;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter@Getter
public class UpdateCommentRequest {
    private String commentId;
    private String comment;
    private LocalDateTime updatedAt;
}
