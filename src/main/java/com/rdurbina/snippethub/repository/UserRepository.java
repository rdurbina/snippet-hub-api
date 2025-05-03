package com.rdurbina.snippethub.repository;

import com.rdurbina.snippethub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);
}
