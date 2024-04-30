package com.example.manageaid.repository;

import com.example.manageaid.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT t.* FROM tasks t WHERE user_id = ?1 and done = ?2", nativeQuery = true)
    List<Task> getTasksForUser(Long userId, Boolean done);





}
