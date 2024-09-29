package com.avengersblog.Services.PostService;

import com.avengersblog.Data.Model.Category;
import com.avengersblog.Data.Model.Post;
import com.avengersblog.Data.Repository.PostRepository;
import com.avengersblog.Dto.request.Post.UploadPostRequest;
import com.avengersblog.Exceptions.PostExceptions.titleAlreadyExistException;
import com.avengersblog.Exceptions.PostExceptions.titleNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
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
    private PostServiceImplementation postServiceImpl;

    private PostRepository postRepository;

    @Test
    public void testUserCanUploadPost() {
        UploadPostRequest postRequest = new UploadPostRequest();
        Path path = Paths.get("C:\\Users\\OWNER\\OneDrive\\Documents\\AvengersBlog\\src\\main\\resources\\static\\wallhaven-9mdmdw_3840x2160.png");

        try (InputStream inputStream = Files.newInputStream(path)) {
            MultipartFile file = new MockMultipartFile("file", inputStream);
            postRequest.setTitle("Sasuke");
            postRequest.setCategory(Category.ART);
            postRequest.setCaption("this is a picture of sasuke");
            postRequest.setImage(file);

            Post post = postServiceImpl.uploadPost(postRequest);
            assertNotNull(post.getImageUrl());
        } catch (IOException | titleNotFoundException | titleAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUserCanUploadVideoPost() {
        UploadPostRequest postRequest = new UploadPostRequest();
        Path path = Paths.get("C:\\Users\\OWNER\\OneDrive\\Documents\\AvengersBlog\\src\\main\\resources\\static\\test.mp4");

        try (InputStream inputStream = Files.newInputStream(path)) {
            MultipartFile file = new MockMultipartFile("file", "test.mp4", "video/mp4", inputStream);
            postRequest.setTitle("Tiktok");
            postRequest.setCategory(Category.ART);
            postRequest.setCaption("this is a video from tiktok");
            postRequest.setImage(file);

            Post post = postServiceImpl.uploadPost(postRequest);
            assertNotNull(post.getImageUrl());
        } catch (IOException | titleNotFoundException | titleAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUserCanNotUploadPostWithTheSameTitle() throws titleAlreadyExistException, IOException, titleNotFoundException {
        UploadPostRequest postRequest = new UploadPostRequest();
        postRequest.setTitle("romi");
        postRequest.setCategory(Category.ART);
        postRequest.setCaption("this is a picture of sasuke");
        postRequest.setImage(createTestImage());
        postServiceImpl.uploadPost(postRequest);

        postRequest.setTitle("romi");
        postRequest.setCategory(Category.ART);
        postRequest.setCaption("this is a picture of sasuke");
        postRequest.setImage(createTestImage());


        titleAlreadyExistException titleExists = assertThrows(titleAlreadyExistException.class, () -> {
            postServiceImpl.uploadPost(postRequest);
        });
        assertEquals("This title already exists", titleExists.getMessage());
    }

    private MultipartFile createTestImage() throws IOException {
        byte[] bytes = new byte[1024]; // 1 KB image
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return new MockMultipartFile("image", "test.jpg", MediaType.IMAGE_JPEG_VALUE, inputStream);
    }
    }

