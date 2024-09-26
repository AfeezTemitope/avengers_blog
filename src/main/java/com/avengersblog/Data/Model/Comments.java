package com.avengersblog.Data.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String comment;
    private LocalDateTime createdAt;
    @OneToOne
    private User userId;
    @ManyToOne
    private Post postId;
}
