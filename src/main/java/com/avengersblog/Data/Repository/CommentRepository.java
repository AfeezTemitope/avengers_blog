package com.avengersblog.Data.Repository;

import com.avengersblog.Data.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
