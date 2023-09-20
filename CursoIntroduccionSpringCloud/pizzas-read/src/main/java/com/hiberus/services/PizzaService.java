package com.hiberus.services;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.PizzaUnauthorizedException;
import com.hiberus.modelos.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PizzaService {

    List<Pizza> getAllPizzas(String password) throws PizzaUnauthorizedException;

    PizzaDTO getPizza(UUID id) throws PizzaNotFoundException;

    List<PizzaDTO> getAllPizzasDTO();
}
