package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Ent;
import com.example.manageaid.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class EntityController {

    @Autowired
    private EntityRepository entityRepository;

    @PostMapping("/entities")
    Ent newUser(@RequestBody Ent newEnt) {
        return entityRepository.save(newEnt);
    }

    @GetMapping("/entities")
    List<Ent> getAllUsers() {
        return entityRepository.findAll();
    }

    @GetMapping("/entities/{id}")
    Ent getUserById(@PathVariable Long id) {
        return entityRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(id));
    }

    @PutMapping("/entities/{id}")
    Ent updateUser(@RequestBody Ent newEnt, @PathVariable Long id) {
        return entityRepository.findById(id)
                .map(entity -> {
                    entity.setName(newEnt.getName());
                    entity.setEmail(newEnt.getEmail());
                    return entityRepository.save(entity);
                }).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @DeleteMapping("/entities/{id}")
    String deleteUser(@PathVariable Long id) {
        if(!entityRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        entityRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }
}
