package com.avengersblog.Services;

import com.avengersblog.Data.Model.Comments;
import com.avengersblog.Data.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comments> getCommentsByPostId(Long postId)  {
        return commentRepository.findByPostId(postId);

    }
}
