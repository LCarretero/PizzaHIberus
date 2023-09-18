package com.hiberus.clientes;

import com.hiberus.dto.PizzaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "pizzas")
public interface ClientePizzas {
    @GetMapping(value = "/pizzas/obtenerPizzasPorUsuario")
    ResponseEntity<List<PizzaDto>> obtenerPizzasPorUsuario(@RequestParam Integer idUsuario);
}
