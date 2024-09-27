package com.avengersblog.Services.PostService;

import com.avengersblog.Data.Model.Category;
import com.avengersblog.Data.Model.Post;
import com.avengersblog.Data.Repository.PostRepository;
import com.avengersblog.Dto.request.Post.DeletePostRequest;
import com.avengersblog.Dto.request.Post.UpdatePostRequest;
import com.avengersblog.Dto.request.Post.UploadPostRequest;
import com.avengersblog.Dto.response.Post.DeletePostResponse;
import com.avengersblog.Dto.response.Post.UpdatePostResponse;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService {

    private final Cloudinary cloudinary;
    private final PostRepository postRepo;

    @Override
    public Post uploadPost(UploadPostRequest postRequest) throws IOException {
        Post post = buildPost(postRequest);
        String url = uploadContent(postRequest.getImage());
        post.setImageUrl(url);
        return postRepo.save(post);
    }

    private Post buildPost(UploadPostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setCaption(postRequest.getCaption());
        post.setCreatedAt(LocalDateTime.now());
        post.setCategory(postRequest.getCategory());
        return post;
    }

    private String uploadContent(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        String resourceType;

        if (contentType != null && contentType.startsWith("video/")) resourceType = "video";
        else if (contentType != null && contentType.startsWith("image/")) resourceType = "image";
        else throw new RuntimeException("Invalid file type. Only images and videos are supported.");

        Map<String, Object> params = Map.of("public_id", UUID.randomUUID().toString(), "resource_type", resourceType);
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
        return uploadResult.get("url").toString();
    }


//    @Override
//    public Post uploadPost(UploadPostRequest postRequest) throws IOException {
//       Post post = buildPost(postRequest);
//       String url = uploadContent(postRequest.getImage());
//       post.setImageUrl(url);
//       return postRepo.save(post);
//    }
//    private Post buildPost (UploadPostRequest postRequest){
//        Post post = new Post();
//        post.setTitle(postRequest.getTitle());
//        post.setCaption(postRequest.getCaption());
//        post.setCreatedAt(LocalDateTime.now());
//        post.setCategory(postRequest.getCategory());
//        return post;
//    }
//    private String uploadContent(MultipartFile file) throws IOException {
//        return cloudinary.uploader().upload(file.getBytes(), Map.of("public_id", UUID.randomUUID().toString())).get("url").toString();
//    }

    @Override
    public UpdatePostResponse updatePost(UpdatePostRequest updateRequest) {
        return null;
    }

    @Override
    public Post findPostByTitle(String title) {
        return null;
    }

    @Override
    public Post findPostById(Long id) {
        return null;
    }

    @Override
    public DeletePostResponse deletePostById(DeletePostRequest deleteRequest) {
        return null;
    }

    @Override
    public DeletePostResponse deletePostByTitle(DeletePostRequest deleteRequest) {
        return null;
    }

    @Override
    public DeletePostResponse deleteAllPosts(DeletePostRequest deleteRequest) {
        return null;
    }

    @Override
    public List<Post> displayAllPosts() {
        return List.of();
    }

    @Override
    public List<Post> findPostsByCategory(Category category) {
        return List.of();
    }

    @Override
    public void likePost(Long like) {

    }

}
