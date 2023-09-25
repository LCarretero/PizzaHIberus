package com.hiberus.services;

import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.modelos.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PizzaReadService {

    List<Pizza> getAllPizzas();

    Pizza getPizza(UUID id) throws PizzaNotFoundException;

    List<Pizza> getFavourites(List<UUID> pizzaIdList);

}
