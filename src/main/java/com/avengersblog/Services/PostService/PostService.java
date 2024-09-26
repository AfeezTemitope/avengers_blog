package com.avengersblog.Services.PostService;

import com.avengersblog.Data.Model.Category;
import com.avengersblog.Data.Model.Post;
import com.avengersblog.Dto.request.Post.DeletePostRequest;
import com.avengersblog.Dto.request.Post.UpdatePostRequest;
import com.avengersblog.Dto.request.Post.UploadPostRequest;
import com.avengersblog.Dto.response.Post.DeletePostResponse;
import com.avengersblog.Dto.response.Post.UpdatePostResponse;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post uploadPost(UploadPostRequest postRequest) throws IOException;
    UpdatePostResponse updatePost(UpdatePostRequest updateRequest);
    Post findPostByTitle(String title);
    Post findPostById(Long id);
    DeletePostResponse deletePostById(DeletePostRequest deleteRequest);
    DeletePostResponse deletePostByTitle(DeletePostRequest deleteRequest);
    DeletePostResponse deleteAllPosts(DeletePostRequest deleteRequest);
    List<Post> displayAllPosts();
    List<Post> findPostsByCategory(Category category);
    void likePost(Long like);
}
