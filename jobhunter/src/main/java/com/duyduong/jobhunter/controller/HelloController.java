package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.util.error.IdInvalidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String getHello() throws IdInvalidException {
        if (true)
            throw new IdInvalidException("check exception in HelloController");
        return "Hello from HelloController";

    }
}
