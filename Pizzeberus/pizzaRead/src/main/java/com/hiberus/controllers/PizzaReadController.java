package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.mapper.PizzaMapper;
import com.hiberus.services.PizzaService;
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

    private final PizzaService pizzaService;

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizza(@PathVariable(name = "id") UUID id) {
        try {
            return ResponseEntity.ok(PizzaMapper.INSTANCE.mapToDTO(pizzaService.getPizza(id)));
        } catch (PizzaNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        return ResponseEntity.ok(pizzaService.getAllPizzas().stream().map(PizzaMapper.INSTANCE::mapToDTO).collect(Collectors.toList()));
    }


    @GetMapping(value = "/favs")
    public ResponseEntity<List<PizzaDTO>> obtainFavouritesPizzas(@RequestBody List<UUID> idPizzas) {
        return ResponseEntity.ok(pizzaService.getFavourites(idPizzas).stream()
                .map(PizzaMapper.INSTANCE::mapToDTO)
                .collect(Collectors.toList()));
    }

}