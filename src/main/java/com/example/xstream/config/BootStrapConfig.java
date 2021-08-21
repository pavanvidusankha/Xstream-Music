package com.example.xstream.config;


import com.example.xstream.models.Role;
import com.example.xstream.models.User;
import com.example.xstream.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

public class BootStrapConfig {
    @Bean @Autowired
    CommandLineRunner commandLineRunner(UserService userService){
        return args -> {
            //adding the roles
            userService.saveRole(new Role(null,"ADMIN"));
            userService.saveRole(new Role(null,"USER"));

            //Adding the users
//            userService.addNewUser(new User(null,"Pavan Samaranayake","psam","123",new ArrayList<>()));
//            userService.addNewUser(new User(null,"John Doe","jdoe","1234",new ArrayList<>()));
//            userService.addNewUser(new User(null,"Alex Holder","aholder","1111",new ArrayList<>()));
            userService.addNewUser(new User("psam","1234","Pavan","Samaranayake","psam@xstream.com"));
            userService.addNewUser(new User("jdoe","123","John","Doe","jdoe@xstream.com"));
            userService.addNewUser(new User("aholder","12","Alex","Holder","aholder@xstream.com"));

            //adding roles to the users
            userService.addRoleToUser("psam","ADMIN");
            userService.addRoleToUser("psam","USER");
            userService.addRoleToUser("jdoe","USER");


        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
