package com.hiberus.services;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaBadRequestException;
import com.hiberus.exceptions.PizzaConflictException;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.PizzaUnauthorizedException;
import com.hiberus.modelos.Pizza;

import java.util.List;

public interface PizzaWriteService {
    Pizza createPizza(String password, String name) throws PizzaUnauthorizedException, PizzaConflictException;

    Pizza updatePizza(String password, Pizza pizza) throws PizzaNotFoundException, PizzaBadRequestException;
}

