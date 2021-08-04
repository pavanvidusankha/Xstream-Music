package com.example.xstream.controllers;

import com.example.xstream.models.User;
import com.example.xstream.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
      this.userService=new UserService();
    }
    @GetMapping
    public List<User> getUsers(){
     return userService.getUsers();
    }
}
