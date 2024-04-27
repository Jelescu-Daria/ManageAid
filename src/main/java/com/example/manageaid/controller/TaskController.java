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

    @PostMapping("/tasks")
    Task newUser(@RequestBody Task newTask) {
        return taskRepository.save(newTask);
    }

    @GetMapping("/tasks")
    List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    Task getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(id));
    }

    @PutMapping("/tasks/{id}")
    Task updateTask(@RequestBody Task newTask, @PathVariable Long id) {
        return taskRepository.findById(id)
                .map(entity -> {
                    entity.setName(newTask.getName());
                    entity.setDetails(newTask.getDeadline());
                    entity.setDeadline(newTask.getDeadline());
                    entity.setDone(newTask.getDone());
                    return taskRepository.save(entity);
                }).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @DeleteMapping("/tasks/{id}")
    String deleteUser(@PathVariable Long id) {
        if(!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        taskRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }
}
