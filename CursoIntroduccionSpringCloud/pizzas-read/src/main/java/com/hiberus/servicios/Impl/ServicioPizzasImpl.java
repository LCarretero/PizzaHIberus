package com.hiberus.servicios.Impl;

import com.hiberus.dto.PizzaDto;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizza;
import com.hiberus.servicios.ServicioPizzas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPizzasImpl implements ServicioPizzas {

    @Autowired
    RepositorioPizza repositorioPizza;

    @Override
    public List<Pizza> obtenerPizzas() {
        return repositorioPizza.findAll();
    }

    @Override
    public PizzaDto obtenerPizzasPorId(Long idPizza) throws PizzaNotFoundException {
        Pizza result = repositorioPizza.findById(idPizza).orElse(null);
        if (result == null)
            throw new PizzaNotFoundException();
        return new PizzaDto(result.getId(),result.getNombre());
    }
}
