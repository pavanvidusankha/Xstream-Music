package com.example.xstream.services.interfaces;

import com.example.xstream.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addNewUser(User user);
     void deleteUser(long id);

    void updateUser(long id, String fname, String lname, String email);

    User getUser(long id);
    List<User> getUsers();
}
