package com.example.xstream.controllers;

import com.example.xstream.models.User;
import com.example.xstream.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public List<User> getUsers() {

        return userServiceImpl.getUsers();
    }

    @GetMapping(path = "/{userId}")
    public User getUser(@PathVariable("userId") long id) {
        return userServiceImpl.getUser(id);
    }

    @PostMapping(path = "/users")
    public void registerNewUser(@RequestBody User user) {
        userServiceImpl.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        userServiceImpl.deleteUser(id);
    }

    @PutMapping(path = "/{userId}")
    public void updateUser(@PathVariable("userId") long id, @RequestParam(required = false) String fname, @RequestParam(required = false) String lname, @RequestParam(required = false) String email) {
        userServiceImpl.updateUser(id, fname, lname, email);
    }

    @PatchMapping(path = "/{userId}")
    public void putUser(@PathVariable("userId") long id, @RequestParam(required = false) String fname, @RequestParam(required = false) String lname, @RequestParam(required = false) String email) {
        userServiceImpl.updateUser(id, fname, lname, email);
    }
}
