package com.avengersblog.Services.PostService;

import com.avengersblog.Data.Model.Category;
import com.avengersblog.Data.Model.Post;
import com.avengersblog.Data.Model.User;
import com.avengersblog.Data.Repository.PostRepository;
import com.avengersblog.Data.Repository.UserRepository;
import com.avengersblog.Dto.request.Post.DeletePostRequest;
import com.avengersblog.Dto.request.Post.UpdatePostRequest;
import com.avengersblog.Dto.request.Post.UploadPostRequest;
import com.avengersblog.Dto.response.Post.DeletePostResponse;
import com.avengersblog.Dto.response.Post.UpdatePostResponse;
import com.avengersblog.Exceptions.PostExceptions.PostNotFoundException;
import com.avengersblog.Exceptions.PostExceptions.titleAlreadyExistException;
import com.avengersblog.Exceptions.PostExceptions.titleNotFoundException;
import com.avengersblog.Exceptions.UserExceptions.UserNotFoundException;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
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
    private final UserRepository userRepo;

    @Override
    public Post uploadPost(UploadPostRequest postRequest) throws IOException, titleNotFoundException, titleAlreadyExistException {
        Post post = buildPost(postRequest);
        String url = uploadContent(postRequest.getImage());
        post.setImageUrl(url);
        return postRepo.save(post);
    }

    private Post buildPost(UploadPostRequest postRequest) throws titleNotFoundException, titleAlreadyExistException {
        Post post = new Post();
        boolean isValid = validate(postRequest.getTitle());
        if(isValid) post.setTitle(postRequest.getTitle());

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

    private boolean validate(String title) throws  titleAlreadyExistException {
        Post foundTitle = postRepo.findPostByTitle(title);
        if (foundTitle == null) return true;
        else throw new titleAlreadyExistException("This title already exists");
    }

    @Override
    public UpdatePostResponse updatePost(UpdatePostRequest updateRequest) throws PostNotFoundException {
        Post foundPost = postRepo.findPostByTitle(updateRequest.getTitle());
        if(foundPost != null){
            foundPost.setCaption(updateRequest.getCaption());
            foundPost.setCategory(updateRequest.getCategory());
            foundPost.setTitle(updateRequest.getTitle());
        }
        else throw new PostNotFoundException("Post is nowhere to be found");
        postRepo.save(foundPost);
        return new UpdatePostResponse("Your post has been updated");
    }

    @Override
    public Post findPostByTitle(String title) {
        return postRepo.findPostByTitle(title);
    }

    @Override
    public DeletePostResponse deletePostByTitle(DeletePostRequest deleteRequest) throws PostNotFoundException, UserNotFoundException {
        if(validateUserCredentials(deleteRequest.getUserName(), deleteRequest.getPassword())){
            Post foundPost = postRepo.findPostByTitle(deleteRequest.getTitle());
            if(foundPost != null) postRepo.delete(foundPost);
            else throw new PostNotFoundException("Post is nowhere to be found");
        }
        return new DeletePostResponse("Post has successfully been deleted");
    }

    private boolean validateUserCredentials(String userName, String password) throws UserNotFoundException {
        User foundUser = userRepo.findUserByUserNameAndPassword(userName, password);
        if(foundUser != null) return true;
        else throw new UserNotFoundException("Could not Identify User");
    }

    @Override
    public List<Post> displayAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public List<Post> findPostsByCategory(Category category) {
        return postRepo.findPostByCategory(category);
    }
}
