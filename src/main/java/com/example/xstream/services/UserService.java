package com.example.xstream.services;

import com.example.xstream.models.User;

public interface UserService {
    void addNewUser(User user);
     void deleteUser(long id);

    void updateUser(long id, String fname, String lname, String email);
}
