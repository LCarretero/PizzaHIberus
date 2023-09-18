package com.hiberus.controladores;

import com.hiberus.dto.PizzaDto;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.modelos.Pizza;
import com.hiberus.servicios.ServicioPizzas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/pizzas")
public class ControladorPizzaRead {

    @Autowired
    ServicioPizzas servicioPizzas;

    @GetMapping(value = "/all")
    ResponseEntity<List<PizzaDto>> obtenerPizzas() {
        List<Pizza> listaPizzas = servicioPizzas.obtenerPizzas();
        List<PizzaDto> listaPizzasDto = new ArrayList<>();
        for (Pizza pizza : listaPizzas) {
            PizzaDto pizzaDto = PizzaDto.builder()
                    .id(pizza.getId())
                    .nombre(pizza.getNombre())
                    .build();
            listaPizzasDto.add(pizzaDto);
        }
        return new ResponseEntity<>(listaPizzasDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<PizzaDto> obtenerPizzasPorId(@RequestParam Long idPizza) {
        try {
            PizzaDto result = servicioPizzas.obtenerPizzasPorId(idPizza);
            return ResponseEntity.ok(result);
        } catch (PizzaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
