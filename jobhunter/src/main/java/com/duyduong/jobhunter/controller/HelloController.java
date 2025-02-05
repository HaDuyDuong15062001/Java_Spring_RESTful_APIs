package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.util.error.IdInvalidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String getHello() throws IdInvalidException {
        return "Hello from HelloController";

    }
}
