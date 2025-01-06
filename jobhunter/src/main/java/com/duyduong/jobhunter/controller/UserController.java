package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return this.userService.handleFindUserById(id);
    }

    @GetMapping("/user")
    public List<User> getAllUser() {
        List<User> arrUsers = this.userService.handleFindAllUser();
        return arrUsers;
    }

    @PostMapping("/user")
    public User createNewUserPostMethod(@RequestBody User userPost) {
        User newUser = this.userService.handleCreateUser(userPost);
        return newUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        this.userService.handleDeleteUser(id);
        return "delete user with id = " + id;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User userPut){
        return this.userService.handleUpdateUser(userPut);
    }
}
