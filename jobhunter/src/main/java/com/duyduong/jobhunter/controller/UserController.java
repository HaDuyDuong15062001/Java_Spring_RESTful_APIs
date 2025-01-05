package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.dto.user.UserCreateDTO;
import com.duyduong.jobhunter.service.user.IUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody UserCreateDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping
    public User deleteUser(@RequestParam Long id) {
        return service.deleteById(id);
    }
}
