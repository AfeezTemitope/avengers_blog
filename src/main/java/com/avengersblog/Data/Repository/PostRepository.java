package com.avengersblog.Data.Repository;

import com.avengersblog.Data.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
