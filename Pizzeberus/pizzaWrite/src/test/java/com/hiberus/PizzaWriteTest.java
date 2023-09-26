package com.hiberus;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.mapper.PizzaMapper;
import com.hiberus.modelos.Pizza;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class PizzaWriteTest {
    @Test
    void pizzaNoArgsConstructorTest() {
        Pizza pizza = new Pizza();
        Assertions.assertNotNull(pizza);
    }

    @Test
    void pizzaSetterTest() {
        Pizza pizza = new Pizza();
        pizza.setName("Olee");
        Assertions.assertEquals("Olee", pizza.getName());
    }

    @Test
    void pizzaAllArgsConstructorTest() {
        Pizza pizza = new Pizza(UUID.randomUUID(), "name");
        Assertions.assertNotNull(pizza);
    }

    @Test
    void pizzaAllArgsConstructorNameTest() {
        Pizza pizza = new Pizza(UUID.randomUUID(), "name");
        Assertions.assertEquals("name", pizza.getName());
    }

    @Test
    void pizzaAllArgsConstructorIdTest() {
        Pizza pizza = new Pizza(UUID.fromString("66666666-6666-6666-6666-666666666666"), "name");
        Assertions.assertEquals("name", pizza.getName());
    }

    @Test
    void pizzaDTONoArgsConstructorTest() {
        PizzaDTO pizzaDTO = new PizzaDTO();
        Assertions.assertNotNull(pizzaDTO);
    }

    @Test
    void pizzaDTOAllArgsConstructorTest() {
        PizzaDTO pizzaDTO = new PizzaDTO();
        Assertions.assertNotNull(pizzaDTO);
    }

    @Test
    void pizzaDTOAllArgsConstructorNameTest() {
        PizzaDTO pizzaDTO = new PizzaDTO("name");
        Assertions.assertEquals("name", pizzaDTO.getName());
    }

    @Test
    void pizzaBuilderNameTest() {
        Pizza pizza = Pizza.builder()
                .name("Pizza de pau sin tomate")
                .build();
        Assertions.assertEquals("Pizza de pau sin tomate", pizza.getName());
    }

    @Test
    void pizzaBuilderIdTest() {
        Pizza pizza = Pizza.builder()
                .id(UUID.fromString("66666666-6666-6666-6666-666666666666"))
                .build();
        Assertions.assertEquals(UUID.fromString("66666666-6666-6666-6666-666666666666"), pizza.getId());
    }

    @Test
    void pizzaMapperTest() {
        UUID id = UUID.fromString("66666666-6666-6666-6666-666666666666");
        Pizza pizza = new Pizza(id, "name");
        PizzaDTO pizzaDTO = PizzaMapper.INSTANCE.mapToDTO(pizza);
        Assertions.assertEquals("name", pizzaDTO.getName());
    }
}
