package com.avengersblog.Data.Repository;

import com.avengersblog.Data.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPostId(Long postId);
}
