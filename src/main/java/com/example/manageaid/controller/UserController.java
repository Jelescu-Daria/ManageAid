package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Task;
import com.example.manageaid.model.User;
import com.example.manageaid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

  @GetMapping("/users/{id}")
  Optional<User> getUser(@PathVariable Long id) {
    return userRepository.findById(id);
  }

  @PutMapping("/users/{id}")
  User updateUser(@RequestBody User newUser, @PathVariable Long id) {
    return userRepository.findById(id)
            .map(entity -> {
              entity.setUsername(newUser.getUsername());
              entity.setEmail(newUser.getEmail());
              entity.setPassword(newUser.getPassword());
              entity.setName(newUser.getName());
              entity.setPhoneNumber(newUser.getPhoneNumber());
              entity.setAddress(newUser.getAddress());
              entity.setDateOfBirth(newUser.getDateOfBirth());
              return userRepository.save(entity);
            }).orElseThrow(()-> new EntityNotFoundException(id));
  }

}
