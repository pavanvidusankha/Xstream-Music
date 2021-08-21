package com.example.xstream.controllers;

import com.example.xstream.config.SecurityConfig;
import com.example.xstream.models.Role;
import com.example.xstream.models.RoleUserDTO;
import com.example.xstream.models.User;
import com.example.xstream.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {
    private final UserService userService;
    private final SecurityConfig securityConfig;

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//
//        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
//    }

//    @PostMapping("/users")
//    public ResponseEntity<User> saveUser(@RequestBody User user) {
//
//        return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.CREATED);
//    }


    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/security/roles").toUriString());
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
    }

    @PostMapping("/users/roles")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleUserDTO dtoObj) {
        userService.addRoleToUser(dtoObj.getUsername(), dtoObj.getRoleName());
        return new ResponseEntity<>("Added Roles to the user " + dtoObj.getUsername(), HttpStatus.OK);
    }

    @GetMapping("/tokens")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        securityConfig.refreshToken(request,response);
    }

}