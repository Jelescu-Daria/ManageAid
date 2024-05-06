package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Module;
import com.example.manageaid.model.User;
import com.example.manageaid.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepository;

    @GetMapping("/modules")
    public List<Module> getModulesFilteredByRole(@RequestParam String role) {
        return moduleRepository.getModulesByRole(role);
    }

    @GetMapping("/modules/{id}")
    public Module getModuleById(@PathVariable Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(id));
    }


}
