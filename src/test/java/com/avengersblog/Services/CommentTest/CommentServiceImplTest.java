package com.avengersblog.Services.CommentTest;

import com.avengersblog.Dto.request.Comments.CreateCommentRequest;
import com.avengersblog.Dto.request.Comments.DeleteCommentResponse;
import com.avengersblog.Dto.request.Comments.UpdateCommentRequest;
import com.avengersblog.Dto.response.Comments.CreateCommentResponse;
import com.avengersblog.Exceptions.Comments.CommentException;
import com.avengersblog.Services.Comments.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;

    @Test
    void testThatCommentsCanBeAdded(){
        CreateCommentRequest request = new CreateCommentRequest();
        request.setComment("this is the avengers");
        request.setCreatedAt(LocalDateTime.now());
        request.setUserId(request.getUserId());
        request.setPostId(request.getPostId());

        CreateCommentResponse response = commentService.commentsOnPost(request);
        assertNotNull(response);
    }
    @Test
    void testThatCommentCannotBeUploadedWithoutContent(){
        CreateCommentRequest request = new CreateCommentRequest();
        //request.setComment("this is the avengers");
        request.setCreatedAt(LocalDateTime.now());
        request.setUserId(request.getUserId());
        request.setPostId(request.getPostId());
        assertThrows(CommentException.class, () -> commentService.commentsOnPost(request));

    }
    @Test
    void testThatCommentsCanBeUpdated(){
        CreateCommentRequest request = new CreateCommentRequest();
        request.setComment("this is the avengers");
        request.setCreatedAt(LocalDateTime.now());
        request.setUserId(request.getUserId());
        request.setPostId(request.getPostId());
        commentService.commentsOnPost(request);

        UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest();
        updateCommentRequest.setComment("this is the new comment");
        updateCommentRequest.setCommentId(request.getCommentId());
        updateCommentRequest.setUpdatedAt(LocalDateTime.now());
        CreateCommentResponse response = commentService.updateComment(updateCommentRequest);
        assertNotNull(response);
    }
//    @Test
//    void testThatCommentsCanBeDeleted(){
//        CreateCommentRequest request = new CreateCommentRequest();
//        request.setComment("this is the avengers");
//        request.setCreatedAt(LocalDateTime.now());
//        request.setUserId(request.getUserId());
//        request.setPostId(request.getPostId());
//        commentService.commentsOnPost(request);
//        CreateCommentResponse response = commentService.commentsOnPost(request);
//
//        DeleteCommentResponse deleteCommentResponse = commentService.deleteComment(response);
//        assertNotNull(deleteCommentResponse);
//        assertEquals(200, deleteCommentResponse.getStatusCode());
//        deleteCommentResponse.getMessage("deleted successfully");
      @Test
      void testThatCommentsCanBeDeleted(){
        CreateCommentRequest request = new CreateCommentRequest();
        request.setComment("this is the avengers");
        request.setCreatedAt(LocalDateTime.now());
        request.setUserId(request.getUserId());
        request.setPostId(request.getPostId());
        commentService.commentsOnPost(request);

        CreateCommentResponse response = commentService.commentsOnPost(request);

        DeleteCommentResponse deleteCommentResponse = commentService.deleteComment(response);
//       deleteCommentResponse.getMessage("deleted successfully");
    }

//       CreateCommentResponse response = commentService.commentsOnPost(request);
//       DeleteCommentResponse deleteCommentResponse = commentService.deleteComment(response);
//       deleteCommentResponse.getMessage("deleted successfully");
  }


