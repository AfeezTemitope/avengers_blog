package com.avengersblog.Data.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int likeCount;
    private String imageUrl;
    private String caption;
    private Category category;
    private LocalDateTime createdAt;
    @ManyToMany
    List<User> sharedWith = new ArrayList<>();
   @ManyToMany
    List<User> likedPosts = new ArrayList<>();
}
