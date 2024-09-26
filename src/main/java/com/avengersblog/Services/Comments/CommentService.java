package com.avengersblog.Services.Comments;

import com.avengersblog.Dto.request.Comments.CreateCommentRequest;
import com.avengersblog.Dto.request.Comments.DeleteCommentResponse;
import com.avengersblog.Dto.request.Comments.UpdateCommentRequest;
import com.avengersblog.Dto.response.Comments.CreateCommentResponse;


public interface CommentService {
    CreateCommentResponse commentsOnPost(CreateCommentRequest request);

    CreateCommentResponse updateComment(UpdateCommentRequest updateCommentRequest);

    DeleteCommentResponse deleteComment(CreateCommentResponse response);
}
