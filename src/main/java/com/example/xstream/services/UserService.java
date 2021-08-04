package com.example.xstream.services;

import com.example.xstream.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserInterface{
    public List<User> getUsers(){
        return List.of(
                new User("psam","pavan","samaranayake","psam@xstream.com")
        );
    }
}
