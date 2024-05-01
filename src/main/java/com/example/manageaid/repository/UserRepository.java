package com.example.manageaid.repository;

import com.example.manageaid.model.ERole;
import com.example.manageaid.model.Role;
import com.example.manageaid.model.Task;
import com.example.manageaid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query(value =
          "SELECT u.* " +
          "FROM users u JOIN user_roles ur on u.id = ur.user_id " +
                  "     JOIN roles r on r.id = ur.role_id " +
          "WHERE r.name = ?1", nativeQuery = true)
  List<User> getUsersByRole(String role);

}
