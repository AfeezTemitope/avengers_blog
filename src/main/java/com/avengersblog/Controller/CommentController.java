package com.avengersblog.Controller;

import com.avengersblog.Data.Model.Comments;
import com.avengersblog.Data.Repository.CommentRepository;
import com.avengersblog.Services.Comments.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/api/posts/{postId}/comments")
    public ResponseEntity<List<Comments>> getComments(@PathVariable Long postId) {
        List<Comments> comments = commentRepository.findByPostId(postId);
        return ResponseEntity.ok(comments);
    }

}
