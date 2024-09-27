package com.avengersblog.Data.Repository;

import com.avengersblog.Data.Model.User;
import com.avengersblog.Dto.request.User.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Optional <User> findUserByUserName(String username);
    User findUserById(long id);
    User findByEmail(String email);
}
