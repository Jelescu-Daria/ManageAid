package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Task;
import com.example.manageaid.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/users/{userId}/tasks")
    List<Task> getAllTasksFilteredByDone(@PathVariable Long userId, @RequestParam Boolean done) {
        return taskRepository.getTasksForUser(userId, done);
    }

    @GetMapping("/users/{userId}/tasks/{id}")
    Task getTaskById(@PathVariable Long id, @PathVariable String userId) {
        return taskRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(id));
    }

    @PostMapping("/users/{userId}/tasks")
    Task addTask(@RequestBody Task newTask) {
        return taskRepository.save(newTask);
    }

    @PutMapping("/users/{userId}/tasks/{id}")
    Task updateTask(@RequestBody Task newTask, @PathVariable Long id) {
        return taskRepository.findById(id)
                .map(entity -> {
                    entity.setName(newTask.getName());
                    entity.setDetails(newTask.getDetails());
                    entity.setDeadline(newTask.getDeadline());
                    entity.setDone(newTask.getDone());
                    entity.setUserId(newTask.getUserId());
                    return taskRepository.save(entity);
                }).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @DeleteMapping("/users/{userId}/tasks/{id}")
    String deleteTask(@PathVariable Long id, @PathVariable String userId) {
        if(!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        taskRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }

}
