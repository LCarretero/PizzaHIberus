package com.hiberus.clients;


import com.hiberus.dto.PizzaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "pizzasRead")
public interface ClientsPizzas {
    @GetMapping(value = "/pizzas/favs")
    ResponseEntity<List<PizzaDTO>> obtainFavouritesPizzas(@RequestBody List<UUID> idPizzas);
    @GetMapping(value = "/pizzas/all")
    ResponseEntity<List<PizzaDTO>> obtainAllPizzas();
}
