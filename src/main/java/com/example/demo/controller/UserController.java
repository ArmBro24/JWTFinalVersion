package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // Define base path for user-related endpoints
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET endpoint to retrieve user by name
    @GetMapping("/name/{name}")
    public UserDTO getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/id/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id); // Calls a service method to fetch user by ID
    }

    // GET endpoint to retrieve all users (Optional)
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted.");
    }
}

