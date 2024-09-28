package com.avengersblog.Data.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;



import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String userName;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isLoggedIn;
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled = true;
    private String googleId;
    private String pictureUrl;
    private String name;


    @OneToMany
    private List <Post> posts = new ArrayList<>();
    @ManyToMany
    private List <Post> sharedPosts = new ArrayList<>();

    public User(String googleId, String name, String email, String pictureUrl) {
    }
}
