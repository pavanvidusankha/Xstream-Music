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

        List<User> usersList = userServiceImpl.getUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);

    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") long id) {

        User user = userServiceImpl.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody User user) {
        if (user.equals(null)) {
            return new ResponseEntity<String>("Please enter a valid entity", HttpStatus.BAD_REQUEST);
        } else {
            userServiceImpl.addNewUser(user);
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        }
    }

    @PostMapping(path = "/bulk")
    public ResponseEntity<?> addUsers(@RequestBody List<User> userList) {
        if(userList != null && !userList.isEmpty()) {
            userServiceImpl.addAllUsers(userList);
            return new ResponseEntity<>("Users created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Please check the request body", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>("User deleted",HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") long id, @RequestParam(required = false) String fname, @RequestParam(required = false) String lname, @RequestParam(required = false) String email) {

        userServiceImpl.updateUser(id, fname, lname, email);
        User updatedUser = userServiceImpl.getUser(id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    @PatchMapping(path = "/{userId}")
    public ResponseEntity<User> putUser(@PathVariable("userId") long id, @RequestParam(required = false) String fname, @RequestParam(required = false) String lname, @RequestParam(required = false) String email) {
        userServiceImpl.updateUser(id, fname, lname, email);
        User updatedUser = userServiceImpl.getUser(id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
