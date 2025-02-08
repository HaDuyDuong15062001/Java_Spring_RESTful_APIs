package com.duyduong.jobhunter.controller;

import com.duyduong.jobhunter.domain.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    @CrossOrigin
    public String getHello() {
        return "Update Hello from Duy Duong";
    }
}
