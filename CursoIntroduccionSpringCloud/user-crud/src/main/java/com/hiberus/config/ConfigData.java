package com.hiberus.config;

import com.hiberus.modelos.Usuario;
import com.hiberus.repositorios.RepositorioUsuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigData {
    @Bean("ConfigData")
    CommandLineRunner commandLineRunner(RepositorioUsuario repositorioUsuario) {
        return args -> {
            Usuario usuario1 =Usuario.builder()
                    .nombre("Emilio")
                    .pizzasFav(new ArrayList<>())
                    .build();

            Usuario usuario2 =Usuario.builder()
                    .nombre("Lucía")
                    .pizzasFav(new ArrayList<>())
                    .build();

            Usuario usuario3 =Usuario.builder()
                    .nombre("Matías")
                    .pizzasFav(new ArrayList<>())
                    .build();

            repositorioUsuario.saveAll(List.of(usuario1, usuario2,usuario3));
        };
    }
}