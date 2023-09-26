package com.hiberus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PizzaDTO implements Comparable<PizzaDTO> {
    private String name;

    @Override
    public int compareTo(PizzaDTO o) {
        return this.name.compareTo(o.getName());
    }
}
