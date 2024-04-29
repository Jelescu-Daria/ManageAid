package com.example.manageaid.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserRoleController {
  @GetMapping("/customer")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public String customerAccess() {
    return "Customer Content.";
  }

  @GetMapping("/employee")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public String employeeAccess() {
    return "Employee Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
