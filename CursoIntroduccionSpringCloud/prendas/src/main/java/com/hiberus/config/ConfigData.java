package com.hiberus.config;

import com.hiberus.modelos.Prenda;
import com.hiberus.repositorios.RepositorioPrenda;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfigData {
    @Bean("ConfigData")
    CommandLineRunner commandLineRunner(RepositorioPrenda repositorioPrenda) {
        return args -> {
            Prenda prenda1 = Prenda.builder()
                    .nombre("Camiseta")
                    .talla("L")
                    .color("Rojo")
                    .idUsuario(1)
                    .build();

            Prenda prenda2 = Prenda.builder()
                    .nombre("Polo")
                    .talla("L")
                    .color("Negro")
                    .idUsuario(1)
                    .build();

            Prenda prenda3 = Prenda.builder()
                    .nombre("Pantalón de deporte")
                    .talla("M")
                    .color("Blanco")
                    .idUsuario(2)
                    .build();

            Prenda prenda4 = Prenda.builder()
                    .nombre("Gorro")
                    .talla("S")
                    .color("Naranja")
                    .idUsuario(3)
                    .build();

            repositorioPrenda.saveAll(List.of(prenda1,prenda2,prenda3,prenda4));
        };
    }
}
