package com.example.xstream.services;

import com.example.xstream.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    AutoCloseable autoClosable;
    UserServiceImpl userService;


    @BeforeEach
    void setUp() {
        autoClosable = MockitoAnnotations.openMocks(this);
        userService =new UserServiceImpl(userRepository);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoClosable.close();
    }

    @Test
    void getUsers() {
    //when
        userService.getUsers();
    //then
        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    void addNewUser() {
    }

    @Test
    @Disabled
    void deleteUser() {
    }

    @Test
    @Disabled
    void updateUser() {
    }
}