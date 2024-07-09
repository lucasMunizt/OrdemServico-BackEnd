package com.example.testos.repository;


import com.example.testos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,String> {
    //User findByEmail(String email);
    // UserDetails findByEmail(String email);
    Optional<User> findByEmail(String email);
}
