package com.hiberus.config;

import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class ConfigData {
    @Bean("ConfigData")
    CommandLineRunner commandLineRunner(PizzaRepository pizzaRepository) {
        return args -> {
            Pizza pizza1 = Pizza.builder()
                    .name("Barbacoa")
                    .id(UUID.randomUUID())
                    .build();

            Pizza pizza2 = Pizza.builder()
                    .name("4 quesos")
                    .id(UUID.randomUUID())
                    .build();

            Pizza pizza3 = Pizza.builder()
                    .name("Carbonara")
                    .id(UUID.randomUUID())
                    .build();

            Pizza pizza4 = Pizza.builder()
                    .name("Caprichosa")
                    .id(UUID.randomUUID())
                    .build();

            pizzaRepository.saveAll(List.of(pizza1, pizza2, pizza3, pizza4));
        };
    }
}
