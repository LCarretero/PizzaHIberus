package com.hiberus.services;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.modelos.Pizza;

public interface ServicePizza {
    Pizza createPizza(String password, Pizza pizza);

    PizzaDTO updatePizza(String password, Pizza pizza);
}

