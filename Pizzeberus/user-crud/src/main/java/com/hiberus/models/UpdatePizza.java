package com.hiberus.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UpdatePizza {
    private UUID userId;
    private String pizzaIdOrName;
}
