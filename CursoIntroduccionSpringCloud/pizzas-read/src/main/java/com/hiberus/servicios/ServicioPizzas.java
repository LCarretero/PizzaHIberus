package com.hiberus.servicios;

import com.hiberus.dto.PizzaDto;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.modelos.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioPizzas {

    List<Pizza> obtenerPizzas();

    PizzaDto obtenerPizzasPorId(Long idPizza) throws PizzaNotFoundException;
}
