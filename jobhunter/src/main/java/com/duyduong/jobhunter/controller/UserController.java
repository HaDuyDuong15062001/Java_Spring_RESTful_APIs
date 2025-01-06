package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUserPostMethod(@RequestBody User userPost) {
        User newUser = this.userService.handleCreateUser(userPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = this.userService.handleFindUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> arrUsers = this.userService.handleFindAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(arrUsers);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        this.userService.handleDeleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Id " + id + " is deleted");
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User userPut){
        User user = this.userService.handleUpdateUser(userPut);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
