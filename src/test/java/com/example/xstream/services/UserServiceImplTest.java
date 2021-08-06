package com.example.xstream.services;

import com.example.xstream.models.User;
import com.example.xstream.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    //    AutoCloseable autoClosable;
    UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
//        autoClosable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);

    }

//    @AfterEach
//    void tearDown() throws Exception {
//        autoClosable.close();
//    }

    @Test
    void getUsers() {
        //when
        userService.getUsers();
        //then
        verify(userRepository).findAll();
    }

    @Test
    void canAddNewUser() {
        //given
        User testUser = new User("test", "Jake", "Peralta", "jperalta@xstream.com");
        //when
        userService.addNewUser(testUser);
        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User captoredValue = userArgumentCaptor.getValue();

        assertThat(captoredValue).isEqualTo(testUser);

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