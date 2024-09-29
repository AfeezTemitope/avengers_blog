package com.avengersblog.Controller.Post;

import com.avengersblog.Data.Model.Post;
import com.avengersblog.Dto.request.Post.UploadPostRequest;
import com.avengersblog.Dto.response.ApiResponse;
import com.avengersblog.Exceptions.PostExceptions.titleNotFoundException;
import com.avengersblog.Services.PostService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPost(UploadPostRequest uploadPostRequest) {
        try {
            Post post = postService.uploadPost(uploadPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, post),CREATED);
        } catch (IOException | titleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
