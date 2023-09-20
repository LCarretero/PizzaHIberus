package com.hiberus.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserDTO {
    private final String name;
    private final List<PizzaDTO> pizzas;
}
