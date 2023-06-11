package com.blog_api.blog_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.blog_api.blog_api.entities.User;
import com.blog_api.blog_api.services.UserService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);

    }
}