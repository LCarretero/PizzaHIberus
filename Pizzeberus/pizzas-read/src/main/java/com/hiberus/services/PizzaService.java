package com.hiberus.services;

import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.modelos.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PizzaService {

    List<Pizza> getAllPizzas();

    Pizza getPizza(UUID id) throws PizzaNotFoundException;

    List<Pizza> favs(List<UUID> pizzaIdList);

}
