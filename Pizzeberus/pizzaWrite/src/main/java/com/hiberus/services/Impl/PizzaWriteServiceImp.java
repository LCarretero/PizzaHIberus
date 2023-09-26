package com.hiberus.services.Impl;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaBadRequestException;
import com.hiberus.exceptions.PizzaConflictException;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.PizzaUnauthorizedException;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.PizzaRepository;
import com.hiberus.services.PizzaWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PizzaWriteServiceImp implements PizzaWriteService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Value(value = "${AUTHORIZATION}")
    private String AUTHORIZATION;

    @Override
    public Pizza createPizza(String password, String name) throws PizzaUnauthorizedException, PizzaConflictException {
        if (!AUTHORIZATION.equals(password))
            throw new PizzaUnauthorizedException();
        Pizza pizza = getPizzaByName(name);
        if (pizza == null && validName(name))
            return pizzaRepository.save(new Pizza(UUID.randomUUID(), name));
        throw new PizzaConflictException();
    }

    @Override
    public Pizza updatePizza(String password, UUID id, PizzaDTO pizza) throws PizzaNotFoundException, PizzaBadRequestException {
        Pizza pizzaDB = getPizza(id);
        if (pizzaDB == null)
            throw new PizzaNotFoundException();
        if (validName(pizza.getName()))
            return pizzaRepository.save(new Pizza(pizzaDB.getId(), pizza.getName()));
        throw new PizzaBadRequestException();
    }

    //region PRIVATE_METHODS
    private boolean validName(String name) {
        return !name.matches(".*\\d.*") && !name.isBlank() && !name.isEmpty();
    }

    private Pizza getPizza(UUID id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    private Pizza getPizzaByName(String name) {
        return pizzaRepository.findByName(name).orElse(null);
    }

    private List<Pizza> obtainAllPizzas() {
        return pizzaRepository.findAll();
    }
    //endregion
}
