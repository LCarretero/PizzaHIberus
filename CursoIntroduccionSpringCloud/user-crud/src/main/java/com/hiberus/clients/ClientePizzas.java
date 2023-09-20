package com.hiberus.clients;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pizzas")
public interface ClientePizzas {
    //@GetMapping(value = "/pizzas/obtenerPizzasPorUsuario")
    //ResponseEntity<List<PizzaDto>> obtenerPizzasPorUsuario(@RequestParam Integer idUsuario);
}
