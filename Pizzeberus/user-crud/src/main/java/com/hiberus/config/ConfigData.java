package com.hiberus.config;


import com.hiberus.models.User;
import com.hiberus.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Configuration
public class ConfigData {
    @Bean("ConfigData")
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User user1 = User.builder()
                    .name("Emilio")
                    .id(UUID.fromString("b131464f-0c9e-48cb-9bc8-128a4ae4d837"))
                    .favouritesPizzas(new HashSet<>())
                    .build();

            User user2 = User.builder()
                    .name("Lucía")
                    .id(UUID.fromString("ae6011a9-5436-40b4-a1e0-d0fc407d8766"))
                    .favouritesPizzas(new HashSet<>())
                    .build();

            User user3 = User.builder()
                    .name("Matías")
                    .id(UUID.fromString("829e17e6-7aab-4fa8-a76d-7167727c887e"))
                    .favouritesPizzas(new HashSet<>())
                    .build();
            userRepository.saveAll(List.of(user1, user2, user3));
        };
    }
}
