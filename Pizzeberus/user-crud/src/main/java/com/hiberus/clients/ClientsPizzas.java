package com.hiberus.clients;


import com.hiberus.dto.PizzaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "pizzasRead")
public interface ClientsPizzas {
    @GetMapping(value = "/pizzasRead/favs")
    ResponseEntity<List<PizzaDTO>> obtainFavouritesPizzas(@RequestParam List<UUID> idPizzas);
}
