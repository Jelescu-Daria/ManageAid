package com.example.manageaid.repository;

import com.example.manageaid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
