package com.backend;

//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/test-controller")
public class TestController {

    @GetMapping
    public String sayHello() {
        return "Hello from our secured endpoint";
    }

}
