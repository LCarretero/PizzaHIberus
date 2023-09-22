package com.hiberus.services.Impl;

import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.PizzaRepository;
import com.hiberus.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Value(value = "${PASSWORD}")
    private String PASSWORD;

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
    public List<Pizza> favs(List<UUID> pizzaIdList) {
        return pizzaIdList.stream()
                .map(pizzaId -> pizzaRepository.findById(pizzaId).orElse(null))
                .collect(Collectors.toList());
    }
}
