package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.User;
import com.example.manageaid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public List<User> getUsersFilteredByRole(@RequestParam String role) {
      return userRepository.getUsersByRole(role);
  }

  @DeleteMapping("/users/{id}")
  String deleteUser(@PathVariable Long id) {
    if(!userRepository.existsById(id)) {
      throw new EntityNotFoundException(id);
    }
    userRepository.deleteById(id);
    return "Entity with id " + id + " has been successfully deleted.";
  }

}
