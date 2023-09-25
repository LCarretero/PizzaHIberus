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
                    .id(UUID.fromString("241caf6e-45de-4e05-8d36-7a37265739f5"))
                    .build();

            Pizza pizza2 = Pizza.builder()
                    .name("4 Quesos")
                    .id(UUID.fromString("3898375f-7044-468f-b652-10aa42ab6f4b"))
                    .build();

            Pizza pizza3 = Pizza.builder()
                    .name("Carbonara")
                    .id(UUID.fromString("d37d72ff-c1df-49bb-870d-62160f702798"))
                    .build();

            Pizza pizza4 = Pizza.builder()
                    .name("Caprichosa")
                    .id(UUID.fromString("9f5473e0-2558-44fd-9fac-fc683b116307"))
                    .build();

            Pizza pizza5 = Pizza.builder()
                    .name("Celia Diabola")
                    .id(UUID.fromString("66666666-6666-6666-6666-666666666666"))
                    .build();

            pizzaRepository.saveAll(List.of(pizza1, pizza2, pizza3, pizza4, pizza5));
        };
    }
}
