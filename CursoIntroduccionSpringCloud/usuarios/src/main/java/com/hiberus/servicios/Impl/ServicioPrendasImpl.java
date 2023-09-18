package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClientePrendas;
import com.hiberus.dto.PrendaDto;
import com.hiberus.servicios.ServicioPrendas;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("feign-prendas")
@AllArgsConstructor
@Slf4j
public class ServicioPrendasImpl implements ServicioPrendas {
    private final ClientePrendas clientePrendas;

    @CircuitBreaker(name = "prendas",fallbackMethod = "fallBackObtenerPrendasPorUsuario")
    @Override
    public List<PrendaDto> obtenerPrendasPorUsuario(Integer idUsuario) {
        return clientePrendas.obtenerPrendasPorUsuario(idUsuario).getBody();
    }

    private List<PrendaDto> fallBackObtenerPrendasPorUsuario(Integer idUsuario, Throwable throwable){
        log.info("Lista prenda por defecto");
        return new ArrayList<>();
    }
}
