package com.example.xstream.services;

import com.example.xstream.models.User;
import com.example.xstream.repositories.PlaylistRepository;
import com.example.xstream.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    //    AutoCloseable autoClosable;
    UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;

    @BeforeEach
    void setUp() {
//        autoClosable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, playlistRepository);
        User user = new User("test1", "john", "doe", "jdoe@gmail.com");
        userRepository.save(user);
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
//        User testUser = new User("test", "Jake", "Peralta", "jperalta@xstream.com");
        //when
//        userService.addNewUser(testUser);
        //then
//        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
//
//        verify(userRepository).save(userArgumentCaptor.capture());
//
//        User captoredValue = userArgumentCaptor.getValue();
//
//        assertThat(captoredValue).isEqualTo(testUser);

        //new Test
        User user = new User("test1", "john", "doe", "jdoe@gmail.com");
        userRepository.save(user);


        assertNotNull(userRepository.findUserByEmail(user.getEmail()));




    }

    @Test
    void deleteUser() {

        userRepository.deleteById(2L);
        assertThat(userRepository.existsById(2L)).isFalse();
    }

    @Test
    void updateUser() {

        //User test = userRepository.findById(1L).orElseThrow(() -> new IllegalStateException("No User exists"));
        User test1 = userRepository.findUserByEmail("jdoe@gmail.com").orElseThrow(() -> new IllegalStateException("No User exists"));
        test1.setEmail("test1@xtream.com");
        userRepository.save(test1);

        assertNotEquals("jdoe@gmail.com", userRepository.findUserByEmail("jdoe@gmail.com").get().getEmail());

    }
}