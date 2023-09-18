package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClientePizzas;
import com.hiberus.dto.PizzaDto;
import com.hiberus.servicios.ServicioPizzas;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("feign-pizzas")
@AllArgsConstructor
@Slf4j
public class ServicioPizzasImpl implements ServicioPizzas {
    private final ClientePizzas clientePizzas;

    @CircuitBreaker(name = "pizzas",fallbackMethod = "fallBackObtenerPizzasPorUsuario")
    @Override
    public List<PizzaDto> obtenerPizzasPorUsuario(Integer idUsuario) {
        return clientePizzas.obtenerPizzasPorUsuario(idUsuario).getBody();
    }

    private List<PizzaDto> fallBackObtenerPizzasPorUsuario(Integer idUsuario, Throwable throwable){
        log.info("Lista pizza por defecto");
        return new ArrayList<>();
    }
}
