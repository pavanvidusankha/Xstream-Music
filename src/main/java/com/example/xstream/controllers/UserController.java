package com.example.xstream.controllers;

import com.example.xstream.models.User;
import com.example.xstream.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        List<User> usersList= userServiceImpl.getUsers();
        return new ResponseEntity<>(usersList,HttpStatus.OK);

    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") long id) {

        User user= userServiceImpl.getUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {

        userServiceImpl.addNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") long id, @RequestParam(required = false) String fname, @RequestParam(required = false) String lname, @RequestParam(required = false) String email) {

        userServiceImpl.updateUser(id, fname, lname, email);
        User updatedUser=userServiceImpl.getUser(id);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

    }

    @PatchMapping(path = "/{userId}")
    public ResponseEntity<User> putUser(@PathVariable("userId") long id, @RequestParam(required = false) String fname, @RequestParam(required = false) String lname, @RequestParam(required = false) String email) {
        userServiceImpl.updateUser(id, fname, lname, email);
        User updatedUser=userServiceImpl.getUser(id);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
}
