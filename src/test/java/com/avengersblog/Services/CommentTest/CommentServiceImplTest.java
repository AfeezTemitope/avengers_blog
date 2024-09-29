package com.avengersblog.Services.CommentTest;

import com.avengersblog.Data.Model.*;
import com.avengersblog.Data.Repository.CommentRepository;
import com.avengersblog.Data.Repository.PostRepository;
import com.avengersblog.Data.Repository.UserRepository;
import com.avengersblog.Dto.request.Comments.CreateCommentRequest;
import com.avengersblog.Dto.request.Comments.DeleteCommentResponse;
import com.avengersblog.Dto.request.Comments.UpdateCommentRequest;
import com.avengersblog.Dto.response.Comments.CreateCommentResponse;
import com.avengersblog.Exceptions.Comments.CommentException;
import com.avengersblog.Services.Comments.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;
    @MockBean
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    void testThatCommentsCanBeAdded(){
        Post post = new Post();
        post.setTitle("Sample Post Title");
        post = postRepository.save(post);

        CreateCommentRequest request = new CreateCommentRequest();
        request.setComment("This is the Avengers");
        request.setCreatedAt(LocalDateTime.now());
        request.setUserId(1L);
        request.setPostId(1L);


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
        assertTrue(false, deleteCommentResponse.getMessage());
    }

//       CreateCommentResponse response = commentService.commentsOnPost(request);
//       DeleteCommentResponse deleteCommentResponse = commentService.deleteComment(response);
//       deleteCommentResponse.getMessage("deleted successfully");
  }


