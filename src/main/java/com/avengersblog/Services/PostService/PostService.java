package com.avengersblog.Services.PostService;

import com.avengersblog.Data.Model.Category;
import com.avengersblog.Data.Model.Post;
import com.avengersblog.Dto.request.Post.DeletePostRequest;
import com.avengersblog.Dto.request.Post.UpdatePostRequest;
import com.avengersblog.Dto.request.Post.UploadPostRequest;
import com.avengersblog.Dto.response.Post.DeletePostResponse;
import com.avengersblog.Dto.response.Post.UpdatePostResponse;
import com.avengersblog.Exceptions.PostExceptions.PostNotFoundException;
import com.avengersblog.Exceptions.PostExceptions.titleNotFoundException;
import com.avengersblog.Exceptions.UserExceptions.UserNotFoundException;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post uploadPost(UploadPostRequest postRequest) throws IOException, titleNotFoundException;
    UpdatePostResponse updatePost(UpdatePostRequest updateRequest) throws PostNotFoundException;
    Post findPostByTitle(String title);
    DeletePostResponse deletePostByTitle(DeletePostRequest deleteRequest) throws PostNotFoundException, UserNotFoundException;
    List<Post> displayAllPosts();
    List<Post> findPostsByCategory(Category category);
}
