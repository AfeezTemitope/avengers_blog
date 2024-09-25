package com.avengersblog.Data.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;



import java.util.ArrayList;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    @OneToMany
    private List <Post> posts = new ArrayList<>();
}
