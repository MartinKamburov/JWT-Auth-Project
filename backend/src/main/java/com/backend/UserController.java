package com.backend;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
public class UserController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
