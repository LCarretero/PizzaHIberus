package com.hiberus.clients;


import com.hiberus.dto.PizzaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@FeignClient(name = "pizzaRead")
public interface ClientsPizzas {
    @GetMapping(value = "/pizza/read/favs")
    ResponseEntity<Set<PizzaDTO>> obtainFavouritesPizzas(@RequestParam(name = "idPizzas") Set<UUID> idPizzas);

    @GetMapping("/pizza/read/{id}")
    ResponseEntity<PizzaDTO> getPizza(@PathVariable(name = "id") UUID id);
    @GetMapping("/pizza/read")
    ResponseEntity<UUID> getPizzaByName(@RequestParam(name = "name") String id);
}
