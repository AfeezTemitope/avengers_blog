package com.avengersblog.Services.PostService;

import com.avengersblog.Data.Model.Category;
import com.avengersblog.Data.Model.Post;
import com.avengersblog.Dto.request.Post.UploadPostRequest;
import com.avengersblog.Exceptions.PostExceptions.titleNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostServiceImplementationTest {

    @Autowired
    private PostServiceImplementation postServiceImpl ;

    @Test
    public void testUserCanUploadPost(){
        UploadPostRequest postRequest = new UploadPostRequest();
        Path path = Paths.get("C:\\Users\\OWNER\\OneDrive\\Documents\\AvengersBlog\\src\\main\\resources\\static\\wallhaven-9mdmdw_3840x2160.png");

        try(InputStream inputStream = Files.newInputStream(path)){
            MultipartFile file = new MockMultipartFile("file", inputStream);
            postRequest.setTitle("Sasuke");
            postRequest.setCategory(Category.ART);
            postRequest.setCaption("this is a picture of sasuke");
            postRequest.setImage(file);

            Post post = postServiceImpl.uploadPost(postRequest);
            assertNotNull(post.getImageUrl());
        } catch (IOException | titleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUserCanUploadVideoPost(){
        UploadPostRequest postRequest = new UploadPostRequest();
        Path path = Paths.get("C:\\Users\\OWNER\\OneDrive\\Documents\\AvengersBlog\\src\\main\\resources\\static\\test.mp4");

        try(InputStream inputStream = Files.newInputStream(path)){
            MultipartFile file = new MockMultipartFile("file", "test.mp4", "video/mp4", inputStream);
            postRequest.setTitle("Tiktok");
            postRequest.setCategory(Category.ART);
            postRequest.setCaption("this is a video from tiktok");
            postRequest.setImage(file);

            Post post = postServiceImpl.uploadPost(postRequest);
            assertNotNull(post.getImageUrl());
        } catch (IOException | titleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}