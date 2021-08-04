package com.example.xstream.config;

import com.example.xstream.models.User;
import com.example.xstream.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class studentConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User pavan =new User("psam","Pavan","Samaranayake","psam@xstream.com");
            User test =new User("test","John","Doe","jdoe@xstream.com");

            userRepository.saveAll(
                    List.of(pavan,test)
            );
        };
    }
}
