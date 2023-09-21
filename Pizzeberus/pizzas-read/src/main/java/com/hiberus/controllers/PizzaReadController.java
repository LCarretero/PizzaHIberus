package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.PizzaUnauthorizedException;
import com.hiberus.modelos.Pizza;
import com.hiberus.services.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pizzas")
public class PizzaReadController {

    private PizzaService pizzaService;

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizza(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(pizzaService.getPizza(id));
        } catch (PizzaNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        return ResponseEntity.ok(pizzaService.getAllPizzasDTO());
    }

    @GetMapping()
    public ResponseEntity<List<Pizza>> getPizzasInfo(@RequestHeader(value = "Authorization") String password) {
        try {
            return ResponseEntity.ok(pizzaService.getAllPizzas(password));
        } catch (PizzaUnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
