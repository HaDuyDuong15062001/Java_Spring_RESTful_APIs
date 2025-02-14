package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.domain.dto.ResultPaginationDTO;
import com.duyduong.jobhunter.service.UserService;
import com.duyduong.jobhunter.util.error.IdInvalidException;
import com.turkraft.springfilter.boot.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUserPostMethod(@RequestBody User userPost) {
        String hashPassword = this.passwordEncoder.encode(userPost.getPassword());
        userPost.setPassword(hashPassword);
        User newUser = this.userService.handleCreateUser(userPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = this.userService.handleFindUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<ResultPaginationDTO> getAllUser(
            @Filter Specification<User> spec,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.handleFindAllUser(spec, pageable));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        this.userService.handleDeleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Id " + id + " is deleted");
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User userPut) {
        User user = this.userService.handleUpdateUser(userPut);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
