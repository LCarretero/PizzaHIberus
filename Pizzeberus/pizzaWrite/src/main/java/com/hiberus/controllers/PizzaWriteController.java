package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaBadRequestException;
import com.hiberus.exceptions.PizzaConflictException;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.PizzaUnauthorizedException;
import com.hiberus.mapper.PizzaMapper;
import com.hiberus.modelos.Pizza;
import com.hiberus.services.PizzaWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/pizza/write")
@AllArgsConstructor
@Api(tags = "Pizza Write Operations")
public class PizzaWriteController {

    PizzaWriteService pizzaWriteService;

    @PostMapping
    @ApiOperation(value = "Create a pizza", response = PizzaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pizza created successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 409, message = "Conflict")
    })
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
    @ApiOperation(value = "Update a pizza by ID", response = Pizza.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pizza updated successfully"),
            @ApiResponse(code = 404, message = "Pizza not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
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
