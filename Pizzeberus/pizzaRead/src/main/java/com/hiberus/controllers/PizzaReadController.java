package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.mapper.PizzaMapper;
import com.hiberus.services.PizzaReadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pizza/read")
@AllArgsConstructor
public class PizzaReadController {

    private final PizzaReadService pizzaReadService;

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizza(@PathVariable(name = "id") UUID id) {
        try {
            return ResponseEntity.ok(PizzaMapper.INSTANCE.mapToDTO(pizzaReadService.getPizza(id)));
        } catch (PizzaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        return ResponseEntity.ok(pizzaReadService.getAllPizzas().stream().map(PizzaMapper.INSTANCE::mapToDTO).collect(Collectors.toList()));
    }


    @GetMapping(value = "/favs")
    public ResponseEntity<List<PizzaDTO>> obtainFavouritesPizzas(@RequestParam(name = "idPizzas") List<UUID> idPizzas) {
        return ResponseEntity.ok(pizzaReadService.getFavourites(idPizzas).stream()
                .map(PizzaMapper.INSTANCE::mapToDTO)
                .collect(Collectors.toList()));
    }

}
