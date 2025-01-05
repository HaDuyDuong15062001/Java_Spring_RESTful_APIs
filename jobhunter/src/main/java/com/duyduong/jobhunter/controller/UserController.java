package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/create")
    public String createNewUser() {

        User user = new User();
        user.setEmail("haduyduong@gmail.com");
        user.setFullName("Ha Duy Duong");
        user.setPassword("12345");

        this.userService.handleCreateUser(user);
        return "create user";
    }
}
