package com.hiberus.clients;


import com.hiberus.dto.PizzaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "pizzaRead")
public interface ClientsPizzas {
    @GetMapping(value = "/pizza/read/favs")
    ResponseEntity<List<PizzaDTO>> obtainFavouritesPizzas(@RequestParam(name = "idPizzas") List<UUID> idPizzas);

    @GetMapping("/{id}")
    ResponseEntity<PizzaDTO> getPizza(@PathVariable(name = "id") UUID id);
}
