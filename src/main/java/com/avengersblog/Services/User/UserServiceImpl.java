package com.avengersblog.Services.User;

import com.avengersblog.Data.Model.Post;
import com.avengersblog.Data.Model.User;
import com.avengersblog.Data.Repository.PostRepository;
import com.avengersblog.Data.Repository.UserRepository;
import com.avengersblog.Dto.request.LikePostRequest;
import com.avengersblog.Dto.request.LoginRequest;
import com.avengersblog.Dto.request.SharePostRequest;
import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.response.LikePostResponse;
import com.avengersblog.Dto.response.LoginResponse;
import com.avengersblog.Dto.response.SharedPostResponse;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Services.PostServiceTest.PostServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;


    @Override
    public UpdateUserProFileResponse updateUserProFile(UpdateUserProFilRequest updateUserProFilRequest) {
        findUserByEmail(updateUserProFilRequest.getEmail());
        User user = new User();
        user.setFirstName(updateUserProFilRequest.getFirstName());
        user.setLastName(updateUserProFilRequest.getLastName());
        user.setEmail(updateUserProFilRequest.getEmail());
        user.setPassword(updateUserProFilRequest.getPassword());
        userRepository.save(user);

        UpdateUserProFileResponse updateUserProFileResponse = new UpdateUserProFileResponse();
        updateUserProFileResponse.setEmail(updateUserProFilRequest.getEmail());
        updateUserProFileResponse.setMessage("Successfully updated user profile");
        return updateUserProFileResponse;

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = findUserByUsername(loginRequest.getUsername());
        PasswordValidation(user, loginRequest.getPassword());
        user.setLoggedIn(true);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successfully logged in");
        return loginResponse;
    }

    @Override
    public SharedPostResponse sharePost(Long userId, Long postId, SharePostRequest request) {
        User receiver = findUserId(request.getReceiverId());
        Post post = postRepository.findPOstById(request.getPostId());
        post.getSharedWith().add(receiver);
        postRepository.save(post);
        SharedPostResponse sharedPostResponse = new SharedPostResponse();
        sharedPostResponse.setMessage("Successfully shared post");
        return sharedPostResponse;
    }

    @Override
    public LikePostResponse likePost(Long userId, Long postId, LikePostRequest likePostRequest) {
       Post post = postRepository.findPOstById(postId);
       User liker = findUserId(userId);
       post.getLikedPosts().add(liker);
       postRepository.save(post);

       LikePostResponse likePostResponse = new LikePostResponse();
       likePostResponse.setMessage("successfully liked post");
       return likePostResponse;
    }


    private void PasswordValidation(User user, String password) {
        if (!password.equals(user.getPassword()) && password.trim().isEmpty()) {
            throw new RuntimeException("Invalid Credentials");
        }
    }


    private void findUserByEmail(String email) {
        userRepository.findUserByEmail(email).
                orElseThrow(() -> new RuntimeException(email + "User Not Found"));

    }

    private User findUserByUsername(String username) {
        return userRepository.findUserByUserName(username).
                orElseThrow(() -> new RuntimeException(username + "User Not Found"));
    }

    private User findUserId(Long id) {
        User userId = userRepository.findUserById(id);
        if(userId == null) {
            throw new RuntimeException("User Not Found");
        }
        return userId;
    }
}
