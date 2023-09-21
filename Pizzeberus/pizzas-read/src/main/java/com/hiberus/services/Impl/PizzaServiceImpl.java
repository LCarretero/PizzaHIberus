package com.hiberus.services.Impl;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.PizzaUnauthorizedException;
import com.hiberus.mapper.PizzaMapper;
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
    public List<PizzaDTO> getAllPizzasDTO() {
        return pizzaRepository.findAll().stream().map(PizzaMapper.INSTANCE::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<Pizza> getAllPizzas(String password) throws PizzaUnauthorizedException {
        if (password.equals(PASSWORD))
            return pizzaRepository.findAll();
        throw new PizzaUnauthorizedException();
    }

    @Override
    public PizzaDTO getPizza(UUID idPizza) throws PizzaNotFoundException {
        Pizza result = pizzaRepository.findById(idPizza).orElse(null);
        if (result == null)
            throw new PizzaNotFoundException();
        return PizzaMapper.INSTANCE.mapToDTO(result);
    }
}
