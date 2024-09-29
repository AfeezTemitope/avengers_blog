package com.avengersblog.Controller.Post;

import com.avengersblog.Data.Model.Post;
import com.avengersblog.Dto.request.Post.DeletePostRequest;
import com.avengersblog.Dto.request.Post.UploadPostRequest;
import com.avengersblog.Dto.response.ApiResponse;
import com.avengersblog.Dto.response.Post.DeletePostResponse;
import com.avengersblog.Exceptions.PostExceptions.PostNotFoundException;
import com.avengersblog.Exceptions.PostExceptions.titleAlreadyExistException;
import com.avengersblog.Exceptions.PostExceptions.titleNotFoundException;
import com.avengersblog.Exceptions.UserExceptions.UserNotFoundException;
import com.avengersblog.Services.PostService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPost(@ModelAttribute UploadPostRequest uploadPostRequest) {
        try {
            Post post = postService.uploadPost(uploadPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, post),CREATED);
        } catch (IOException | titleNotFoundException | titleAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<?> deletePostByTitle(@RequestBody DeletePostRequest deletePostRequest) {
        try {
            DeletePostResponse response = postService.deletePostByTitle(deletePostRequest);
            return new ResponseEntity<>(new ApiResponse(true, response.getMessage()), HttpStatus.OK);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(false, "Invalid user credentials"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "An error occurred while deleting the post"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findPost")
    public ResponseEntity<?> findPostByTitle(@RequestParam String title) {
        try {
            Post post = postService.findPostByTitle(title);
            if (post != null) {
                return new ResponseEntity<>(new ApiResponse(true, post), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(false, "Post not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "An error occurred while fetching the post"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allPosts")
    public ResponseEntity<?> displayAllPosts() {
        try {
            List<Post> posts = postService.displayAllPosts();
            if (!posts.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(true, posts), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(false, "No posts found"), HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "An error occurred while fetching the posts"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
