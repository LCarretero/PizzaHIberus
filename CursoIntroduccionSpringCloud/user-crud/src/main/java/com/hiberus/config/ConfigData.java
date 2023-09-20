package com.hiberus.config;


import com.hiberus.models.User;
import com.hiberus.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigData {
    @Bean("ConfigData")
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User user1 =User.builder()
                    .name("Emilio")
                    .favouritesPizzas(new ArrayList<>())
                    .build();

            User user2 =User.builder()
                    .name("Lucía")
                    .favouritesPizzas(new ArrayList<>())
                    .build();

            User user3 =User.builder()
                    .name("Matías")
                    .favouritesPizzas(new ArrayList<>())
                    .build();

            userRepository.saveAll(List.of(user1, user2,user3));
        };
    }
}
