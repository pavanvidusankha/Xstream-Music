package com.example.xstream.repositories;

import com.example.xstream.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository testUserRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void itShouldCheckUserByEmail() {
        User testUser = new User("justin","justin","doe","jdoe@xstream.com");
        long user_id=3;
        testUserRepository.save(testUser);

        //when
        boolean exists = testUserRepository.existsById(user_id);

        //then
        assertThat(exists).isFalse();

    }

    @Test
    void itShouldCheckUserByEmailDoesntExists() {
        User testUser = new User("justin","justin","doe","jdoe@xstream.com");
        long user_id=3;
        testUserRepository.save(testUser);

        //when
        boolean exists = testUserRepository.existsById(user_id);

        //then
        assertThat(exists).isTrue();

    }
}