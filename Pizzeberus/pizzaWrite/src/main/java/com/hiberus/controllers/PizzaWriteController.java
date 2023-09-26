package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaBadRequestException;
import com.hiberus.exceptions.PizzaConflictException;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.PizzaUnauthorizedException;
import com.hiberus.mapper.PizzaMapper;
import com.hiberus.modelos.Pizza;
import com.hiberus.services.PizzaWriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/pizza/write")
@AllArgsConstructor
public class PizzaWriteController {

    PizzaWriteService pizzaWriteService;

    @PostMapping
    public ResponseEntity<PizzaDTO> createPizza(@RequestHeader("Authorization") String password, @RequestBody PizzaDTO pizza) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(PizzaMapper.INSTANCE.mapToDTO(pizzaWriteService.createPizza(password, pizza.getName())));
        } catch (PizzaUnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (PizzaConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@RequestHeader("Authorization") String password, @PathVariable UUID id, @RequestBody PizzaDTO pizza) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pizzaWriteService.updatePizza(password, id, pizza));
        } catch (PizzaNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (PizzaBadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
