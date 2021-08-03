package com.example.xstream.controllers;

import com.example.xstream.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @GetMapping
    public List<User> getUsers(){
        return List.of(
                new User("psam","pavan","samaranayake","psam@xstream.com")
        );
    }
}
