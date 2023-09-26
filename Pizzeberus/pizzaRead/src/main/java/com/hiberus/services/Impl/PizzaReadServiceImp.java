package com.hiberus.services.Impl;

import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.PizzaRepository;
import com.hiberus.services.PizzaReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PizzaReadServiceImp implements PizzaReadService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza getPizza(UUID idPizza) throws PizzaNotFoundException {
        Pizza result = pizzaRepository.findById(idPizza).orElse(null);
        if (result == null) throw new PizzaNotFoundException();
        return result;
    }

    @Override
    public Set<Pizza> getFavourites(Set<UUID> pizzaIdList) {
        return pizzaIdList.stream()
                .map(pizzaId -> pizzaRepository.findById(pizzaId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public UUID getPizzaByName(String name) throws PizzaNotFoundException {
        Pizza pizza = pizzaRepository.findByName(name).orElse(null);
        if (pizza == null)
            throw new PizzaNotFoundException();
        return pizza.getId();
    }
}
