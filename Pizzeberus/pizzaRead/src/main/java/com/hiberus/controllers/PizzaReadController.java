package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.mapper.PizzaMapper;
import com.hiberus.services.PizzaReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pizza/read")
@AllArgsConstructor
@Api(tags = "Pizza Read Operations")
public class PizzaReadController {

    private final PizzaReadService pizzaReadService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a pizza by ID", response = PizzaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pizza found"),
            @ApiResponse(code = 404, message = "Pizza not found")
    })
    public ResponseEntity<PizzaDTO> getPizza(@PathVariable(name = "id") UUID id) {
        try {
            return ResponseEntity.ok(PizzaMapper.INSTANCE.mapToDTO(pizzaReadService.getPizza(id)));
        } catch (PizzaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all pizzas", response = PizzaDTO.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "List of pizzas found")
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        return ResponseEntity.ok(pizzaReadService.getAllPizzas().stream()
                .map(PizzaMapper.INSTANCE::mapToDTO)
                .collect(Collectors.toList()));
    }


    @GetMapping(value = "/favs")
    @ApiOperation(value = "Get favorite pizzas by IDs", response = PizzaDTO.class, responseContainer = "Set")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Favorite pizzas found"),
            @ApiResponse(code = 404, message = "One or more pizzas not found")
    })
    public ResponseEntity<Set<PizzaDTO>> obtainFavouritesPizzas(@RequestParam(name = "idPizzas") Set<UUID> idPizzas) {
        return ResponseEntity.ok(pizzaReadService.getFavourites(idPizzas).stream()
                .map(PizzaMapper.INSTANCE::mapToDTO)
                .collect(Collectors.toSet()));
    }

    @GetMapping
    @ApiOperation(value = "Get a pizza by name", response = UUID.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pizza found by name"),
            @ApiResponse(code = 404, message = "Pizza not found by name")
    })
    public ResponseEntity<UUID> getPizzaByName(@RequestParam(name = "name") String name) {
        try {
            return ResponseEntity.ok(pizzaReadService.getPizzaByName(name));
        } catch (PizzaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
